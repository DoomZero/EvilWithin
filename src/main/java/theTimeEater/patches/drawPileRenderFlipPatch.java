package theTimeEater.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theTimeEater.powers.ReversePower;

@SpirePatch(
        clz= DrawPilePanel.class,
        method="render"
)
public class drawPileRenderFlipPatch {
    public static boolean check(){
        ReversePower pow = (ReversePower) AbstractDungeon.player.getPower(ReversePower.POWER_ID);
        return (pow != null && pow.amount >= 1);
    }

    public static int getPow(){
        ReversePower pow = (ReversePower) AbstractDungeon.player.getPower(ReversePower.POWER_ID);
        if (pow == null) return 1;
        if (pow.amount == -1) return -2;
        return pow.amount;
    }

    public static ExprEditor Instrument() {

        return new ExprEditor() {
            public boolean done = false;

            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getClassName().equals(SpriteBatch.class.getName()) && m.getMethodName().equals("draw") && !done) {
                    System.out.println("DEBUG - PERFORMING DECK FLIP PATCH");
                    String patchName = drawPileRenderFlipPatch.class.getName();
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