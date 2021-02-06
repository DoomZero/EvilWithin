package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;

import java.util.ArrayList;
import theTimeEater.TheTimeEater;
import theTimeEater.TheTimeEater.tempos;

public class EnterTempoAction extends AbstractGameAction {

    TheTimeEater p = (TheTimeEater) AbstractDungeon.player;
    DrawPilePanel drawPile = AbstractDungeon.overlayMenu.combatDeckPanel;
    DiscardPilePanel discardPile = AbstractDungeon.overlayMenu.discardPilePanel;
    tempos direction;

    public EnterTempoAction(tempos tempo) {
        this.direction = tempo;
    }

    @Override
    public void update() {
        //Don't do anything if trying to enter the tempo you're already in
        if (p.tempo == this.direction) {
            isDone = true;
            return;
        }
        //change the contents
        ArrayList<AbstractCard> tmp = p.discardPile.group;
        p.discardPile.group = p.drawPile.group;
        p.drawPile.group = tmp;

        p.tempo = this.direction;

        //flip the icons
        float temp_x = drawPile.target_x;
        float temp_y = drawPile.target_y;
        drawPile.target_x = drawPile.current_x = discardPile.target_x;
        drawPile.target_y = drawPile.current_y = discardPile.target_y;
        discardPile.target_x = discardPile.current_x = temp_x;
        discardPile.target_y = discardPile.current_y = temp_y;

        isDone = true;
    }
}