package theTimeEater.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theTimeEater.TheTimeEater;
import theTimeEater.powers.ReversePower;

import static theTimeEater.util.Wiz.adp;

@SpirePatch(
        clz= DrawPilePanel.class,
        method="render"
)
public class DrawPileRenderFlipPatch {
    public static boolean check(){
        if (adp() instanceof TheTimeEater){
            TheTimeEater tp = (TheTimeEater) adp();
            return tp.tempo == TheTimeEater.tempos.FORWARD;
        }
        ReversePower pow = (ReversePower) AbstractDungeon.player.getPower(ReversePower.POWER_ID);
        return (pow != null && pow.amount >= 0);
    }

    public static int getPow(){
        if (adp() instanceof TheTimeEater){
            TheTimeEater tp = (TheTimeEater) adp();
            if (tp.tempo == TheTimeEater.tempos.REWIND) return -2;
            else return 1;
        }
        ReversePower pow = (ReversePower) AbstractDungeon.player.getPower(ReversePower.POWER_ID);
        int result = 1;
        if (pow == null) return 1;
        if (pow.amount == -1) result = -2;
        return result;
    }

    public static ExprEditor Instrument() {

        return new ExprEditor() {
            public boolean done = false;

            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw") && !done) {
                    System.out.println("DEBUG - PERFORMING DECK FLIP PATCH");
                    String patchName = DrawPileRenderFlipPatch.class.getName();
                    m.replace("{" +
                            "$2 = this.current_x + DECK_X * " + patchName+".getPow();" +
                            "$15 = " + patchName+".check();" +
                            "$proceed($$);" +
                            "}");
                    done = true;
                }
            }
        };
    }
}