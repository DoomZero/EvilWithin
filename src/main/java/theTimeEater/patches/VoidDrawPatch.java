package theTimeEater.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theTimeEater.powers.DrawVoidPower;

import java.util.ArrayList;

@SpirePatch(
        clz = DrawCardAction.class,
        method = "update"
)
public class VoidDrawPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void addVoidBeforeDraw() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractPower q = p.getPower(DrawVoidPower.POWER_ID);
        if (q != null && q.amount >= 1) {
            p.drawPile.addToTop(new VoidCard());
            q.amount--;
            q.updateDescription();
        }

    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "getTopCard");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }
}
