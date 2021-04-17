package theTimeEater.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.vfx.DiscardGlowEffect;
import com.megacrit.cardcrawl.vfx.GameDeckGlowEffect;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theTimeEater.TimeEaterMod;

public class PileRenderFlipPatch {

    public static class draw {
        //copied from DrawPilePanel
        //static final float COUNT_CIRCLE_W = 128.0F * Settings.scale; //no change
        static final float DECK_X = 76.0F * Settings.scale - 64.0F;
        static final float DECK_Y = 74.0F * Settings.scale - 64.0F;
        static final float COUNT_X = 118.0F * Settings.scale;
        //static final float COUNT_Y = 48.0F * Settings.scale; //no change
        static final float COUNT_OFFSET_X = 54.0F * Settings.scale;
        //static final float COUNT_OFFSET_Y = -18.0F * Settings.scale; //no change
        static final float DECK_TIP_X = 50.0F * Settings.scale;
        //static final float DECK_TIP_Y = 470.0F * Settings.scale; //no change
        static final float HITBOX_W = 120.0F * Settings.scale; //no change
        static final float HITBOX_W2 = 450.0F * Settings.scale;

        static final float HITBOX_X = HITBOX_W / 2;
    }

    public static class discard {
        //copied from DiscardPilePanel
        //static final float COUNT_CIRCLE_W = 128.0F * Settings.scale;
        static final float DECK_X = 180.0F * Settings.scale - 64.0F;
        static final float DECK_Y = 70.0F * Settings.scale - 64.0F;
        static final float COUNT_X = 134.0F * Settings.scale;
        //static final float COUNT_Y = 48.0F * Settings.scale;
        static final float COUNT_OFFSET_X = 70.0F * Settings.scale;
        //static final float COUNT_OFFSET_Y = -18.0F * Settings.scale;
        static final float DECK_TIP_X = 1550.0F * Settings.xScale;
        //static final float DECK_TIP_Y = 470.0F * Settings.scale;
        static final float HITBOX_W = 120.0F * Settings.scale;
        static final float HITBOX_W2 = 450.0F * Settings.xScale;

        static final float HITBOX_X = Settings.WIDTH - HITBOX_W / 2;
    }

//    discard active = new discard();

    public static boolean check() {
        return TimeEaterMod.tempo == TimeEaterMod.tempos.FORWARD || TimeEaterMod.tempo == TimeEaterMod.tempos.PAUSE;
    }

    public static double adjustDrawParticles() {
        return check() ? 0 : -discard.DECK_X;
    }

    public static double adjustDiscardParticles() {
        return check() ? 0 : -draw.DECK_X;
    }

    @SpirePatch(
            clz = DrawPilePanel.class,
            method = "render"
    )
    public static class DrawPileRenderFlipPatch {

        public static ExprEditor Instrument() {

            return new ExprEditor() {
                public boolean done = false;

                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw") && !done) {
                        System.out.println("DEBUG - PERFORMING DRAW FLIP PATCH");
                        String patchName = PileRenderFlipPatch.class.getName();
                        //this is currently on line 194 for future reference
                        m.replace("{" +
                                //"$2 = this.current_x + " + patchName+".getDrawAdj();" +
                                "$15 = " + patchName + ".check();" +
                                "$proceed($$);" +
                                "}");
                        done = true;
                    }
                }
            };
        }
    }

//    note: entering pause while in rewind does weird things

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "renderButton"
    )
    public static class DiscardPileRenderFlipPatch {

        public static ExprEditor Instrument() {

            return new ExprEditor() {
                public boolean done = false;

                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    //note: the .equals("draw") part here is referring to drawing the graphic, not the draw pile
                    if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw") && !done) {
                        System.out.println("DEBUG - PERFORMING DISCARD FLIP PATCH");
                        String patchName = PileRenderFlipPatch.class.getName();
                        m.replace("{" +
                                //"$2 = this.current_x + " + patchName+".getDiscardAdj();" +
                                "$15 = " + patchName + ".check();" +
                                "$proceed($$);" +
                                "}");
                        done = true;
                    }
                }
            };
        }
    }

    @SpirePatch(
            clz = DrawPilePanel.class,
            method = "render"
    )
    public static class DrawPileEffectFlipPatch {

        public static ExprEditor Instrument() {

            return new ExprEditor() {
                public boolean done = false;

                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(GameDeckGlowEffect.class.getName()) && m.getMethodName().equals("render") && !done) {
                        System.out.println("DEBUG - PERFORMING DRAW EFFECT FLIP PATCH");
                        String patchName = PileRenderFlipPatch.class.getName();
                        //this is currently on line 194 for future reference
                        m.replace("{" +
                                "$2 = this.current_x + " + patchName + ".adjustDrawParticles();" +
                                "$proceed($$);" +
                                "}");
                        done = true;
                    }
                }
            };
        }
    }

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "renderButton"
    )
    public static class DiscardPileEffectFlipPatch {

        public static ExprEditor Instrument() {

            return new ExprEditor() {
                public boolean done = false;

                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    //note: the .equals("draw") part here is referring to drawing the graphic, not the draw pile
                    if (m.getClassName().equals(DiscardGlowEffect.class.getName()) && m.getMethodName().equals("render") && !done) {
                        System.out.println("DEBUG - PERFORMING DISCARD EFFECT FLIP PATCH");
                        String patchName = PileRenderFlipPatch.class.getName();
                        m.replace("{" +
                                "$2 = this.current_x - 1664.0F * " + Settings.class.getName() + ".scale + " + patchName + ".adjustDiscardParticles();" +
                                "$proceed($$);" +
                                "}");
                        done = true;
                    }
                }
            };
        }
    }

    @SpirePatch(
            clz = DrawPilePanel.class,
            method = "render"
    )
    public static class DrawPileCountFlipPatch {
        @SpirePrefixPatch
        public static void drawCountFlipPatch(DrawPilePanel __instance,
                                              SpriteBatch sb,
                                              @ByRef float[] ___DECK_X,
                                              @ByRef float[] ___DECK_Y,
                                              @ByRef float[] ___COUNT_X,
                                              @ByRef float[] ___COUNT_OFFSET_X,
                                              @ByRef float[] ___DECK_TIP_X,
                                              @ByRef float[] ___HITBOX_W2,
                                              @ByRef Hitbox[] ___hb
        ) {
            if (check()) {
                ___DECK_X[0] = draw.DECK_X;
                ___DECK_Y[0] = draw.DECK_Y;
                ___COUNT_X[0] = draw.COUNT_X;
                ___COUNT_OFFSET_X[0] = draw.COUNT_OFFSET_X;
                ___DECK_TIP_X[0] = draw.DECK_TIP_X;
                ___HITBOX_W2[0] = draw.HITBOX_W2;

                ___hb[0].moveX(draw.HITBOX_X);
            } else {
                ___DECK_X[0] = discard.DECK_X;
                ___DECK_Y[0] = discard.DECK_Y;
                ___COUNT_X[0] = discard.COUNT_X;
                ___COUNT_OFFSET_X[0] = discard.COUNT_OFFSET_X;
                ___DECK_TIP_X[0] = discard.DECK_TIP_X;
                ___HITBOX_W2[0] = discard.HITBOX_W2;

                ___hb[0].moveX(discard.HITBOX_X);
            }
        }
    }

    @SpirePatch(
            clz = DiscardPilePanel.class,
            method = "render"
    )
    public static class DiscardPileCountFlipPatch {
        @SpirePrefixPatch
        public static void discardCountFlipPatch(DiscardPilePanel __instance,
                                                 SpriteBatch sb,
                                                 @ByRef float[] ___DECK_X,
                                                 @ByRef float[] ___DECK_Y,
                                                 @ByRef float[] ___COUNT_X,
                                                 @ByRef float[] ___COUNT_OFFSET_X,
                                                 @ByRef float[] ___DECK_TIP_X,
                                                 @ByRef float[] ___HITBOX_W2,
                                                 @ByRef Hitbox[] ___hb
        ) {
            if (check()) {
                ___DECK_X[0] = discard.DECK_X;
                ___DECK_Y[0] = discard.DECK_Y;
                ___COUNT_X[0] = discard.COUNT_X;
                ___COUNT_OFFSET_X[0] = discard.COUNT_OFFSET_X;
                ___DECK_TIP_X[0] = discard.DECK_TIP_X;
                ___HITBOX_W2[0] = discard.HITBOX_W2;

                ___hb[0].moveX(discard.HITBOX_X);
            } else {
                ___DECK_X[0] = draw.DECK_X;
                ___DECK_Y[0] = draw.DECK_Y;
                ___COUNT_X[0] = draw.COUNT_X;
                ___COUNT_OFFSET_X[0] = draw.COUNT_OFFSET_X;
                ___DECK_TIP_X[0] = draw.DECK_TIP_X;
                ___HITBOX_W2[0] = draw.HITBOX_W2;

                ___hb[0].moveX(draw.HITBOX_X);
            }
        }
    }

}