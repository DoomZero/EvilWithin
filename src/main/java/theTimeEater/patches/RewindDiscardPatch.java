package theTimeEater.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theTimeEater.TheTimeEater;
import theTimeEater.powers.ReversePower;

import java.util.ArrayList;

@SpirePatch(
        clz=AbstractCard.class,
        method="moveToDiscardPile"
)
public class RewindDiscardPatch {
    @SpirePostfixPatch
    public static void discardToDraw(AbstractCard __instance){
        System.out.printf("DEBUG - target_x before: %.5f", __instance.target_x);
        AbstractPlayer p = AbstractDungeon.player;
        if (p instanceof TheTimeEater){
            TheTimeEater tp = (TheTimeEater) p;
            if (tp.tempo == TheTimeEater.tempos.REWIND){
                __instance.target_x = CardGroup.DRAW_PILE_X;
            }
        }
        else {
            AbstractPower revPower = p.getPower(ReversePower.POWER_ID);
            if (revPower != null && revPower.amount >= 1){
                __instance.target_x = CardGroup.DRAW_PILE_X;
            }
        }
        System.out.printf("DEBUG - target_x after: %.5f", __instance.target_x);
    }

    /*private static class discardCardLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }*/


}


