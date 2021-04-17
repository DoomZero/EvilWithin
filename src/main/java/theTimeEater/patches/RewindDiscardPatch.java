package theTimeEater.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import theTimeEater.TimeEaterMod;

@SpirePatch(
        clz = AbstractCard.class,
        method = "moveToDiscardPile"
)
public class RewindDiscardPatch {
    @SpirePostfixPatch
    public static void discardToDraw(AbstractCard __instance) {
        if (TimeEaterMod.tempo == TimeEaterMod.tempos.REWIND) {
            //this is the only line that is important:
            __instance.target_x = CardGroup.DRAW_PILE_X;
        }
    }

    /*private static class discardCardLocator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractDungeon.class, "getCurrRoom");
            return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
        }
    }*/


}


