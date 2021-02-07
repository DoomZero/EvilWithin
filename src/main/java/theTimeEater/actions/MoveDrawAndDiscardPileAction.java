package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.ui.panels.AbstractPanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;

import basemod.ReflectionHacks;
import theTimeEater.powers.ReversePower;

import java.sql.Ref;

public class MoveDrawAndDiscardPileAction extends AbstractGameAction {
    DrawPilePanel drawPile = AbstractDungeon.overlayMenu.combatDeckPanel;
    DiscardPilePanel discardPile = AbstractDungeon.overlayMenu.discardPilePanel;
    ReversePower pow = (ReversePower) AbstractDungeon.player.getPower(ReversePower.POWER_ID);

    @Override
    public void update() {

        if (pow.amount <= 1) {
            //Forward

        }
        float temp_x = drawPile.target_x;
        drawPile.target_x = drawPile.current_x = discardPile.target_x;
        discardPile.target_x = discardPile.current_x = temp_x;

        float temp_y = drawPile.target_y;
        drawPile.target_y = drawPile.current_y = discardPile.target_y;
        discardPile.target_y = discardPile.current_y = temp_y;

        isDone = true;
    }
}