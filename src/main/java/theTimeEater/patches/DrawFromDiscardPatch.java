package theTimeEater.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theTimeEater.TimeEaterMod;

import java.util.ArrayList;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "draw",
        paramtypez = {int.class}
)
public class DrawFromDiscardPatch {
    @SpireInsertPatch(
            locator = drawCardLocator.class,
            localvars = {"c"}
    )
    public static void drawFromDiscard(AbstractPlayer __instance, int drawNum, @ByRef AbstractCard[] c) {
        if (TimeEaterMod.tempo == TimeEaterMod.tempos.REWIND) {
            c[0].current_x = CardGroup.DISCARD_PILE_X;
        }
    }

    private static class drawCardLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "setAngle");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }


}


