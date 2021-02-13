package theTimeEater.patches;

import com.badlogic.gdx.math.Vector2;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import theTimeEater.TheTimeEater;

import java.util.ArrayList;

import static theTimeEater.util.Wiz.adp;
import theTimeEater.TheTimeEater;

public class SoulPatch {
    @SpirePatch(
            clz=Soul.class,
            method="discard",
            paramtypez = {AbstractCard.class, boolean.class}
    )
    public static class DiscardPatch {
        @SpirePostfixPatch()
        public static void patchDiscard(Soul __instance, AbstractCard c, boolean b, @ByRef Vector2[] ___target, @ByRef boolean[] ___rotateClockwise, float ___DRAW_PILE_X, float ___DRAW_PILE_Y){
            if (adp() instanceof TheTimeEater && ((TheTimeEater) adp()).tempo == TheTimeEater.tempos.REWIND) {
                ___target[0] = new Vector2(___DRAW_PILE_X, ___DRAW_PILE_Y);
                ___rotateClockwise[0] = !___rotateClockwise[0];
            }
        }

        /*private static class DiscardLocator extends SpireInsertLocator {
            public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(Soul.class, "setSharedVariables");
                return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
            }
        }*/

    }

    @SpirePatch(
            clz=Soul.class,
            method="shuffle",
            paramtypez = {AbstractCard.class, boolean.class}
    )
    public static class ShufflePatch {
        @SpirePostfixPatch()
        public static void patchShuffle(Soul __instance, AbstractCard c, boolean b1, @ByRef Vector2[] ___pos, @ByRef Vector2[] ___target, @ByRef boolean[] ___rotateClockwise, float ___DISCARD_X, float ___DISCARD_Y, float ___DRAW_PILE_X, float ___DRAW_PILE_Y) {
            if (adp() instanceof TheTimeEater && ((TheTimeEater) adp()).tempo == TheTimeEater.tempos.REWIND) {
                ___pos[0] = new Vector2(___DRAW_PILE_X, ___DRAW_PILE_Y);
                ___target[0] = new Vector2(___DISCARD_X, ___DISCARD_Y);
                ___rotateClockwise[0] = !___rotateClockwise[0];
            }
        }
    }

    @SpirePatch(
            clz=Soul.class,
            method="onToDeck",
            paramtypez = {AbstractCard.class, boolean.class, boolean.class}
    )
    public static class OnToDeckPatch {
        @SpirePostfixPatch()
        public static void patchOnToDeck(Soul __instance, AbstractCard c, boolean b1, boolean b2, @ByRef Vector2[] ___target, @ByRef boolean[] ___rotateClockwise, float ___DISCARD_X, float ___DISCARD_Y) {
            if (adp() instanceof TheTimeEater && ((TheTimeEater) adp()).tempo == TheTimeEater.tempos.REWIND) {
                ___target[0] = new Vector2(___DISCARD_X, ___DISCARD_Y);
                ___rotateClockwise[0] = !___rotateClockwise[0];
            }
        }
    }

    @SpirePatch(
            clz=Soul.class,
            method="onToBottomOfDeck",
            paramtypez = {AbstractCard.class}
    )
    public static class OnToBottomOfDeckPatch {
        @SpirePostfixPatch()
        public static void patchOnToBottomOfDeck(Soul __instance, AbstractCard c, @ByRef Vector2[] ___target, @ByRef boolean[] ___rotateClockwise, float ___DISCARD_X, float ___DISCARD_Y) {
            if (adp() instanceof TheTimeEater && ((TheTimeEater) adp()).tempo == TheTimeEater.tempos.REWIND) {
                ___target[0] = new Vector2(___DISCARD_X, ___DISCARD_Y);
                ___rotateClockwise[0] = !___rotateClockwise[0];
            }
        }
    }
}
