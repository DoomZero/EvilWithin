package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;

import java.util.ArrayList;

import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;
import theTimeEater.TimeEaterMod.tempos;
import theTimeEater.powers.InvisiblePausePower;
import theTimeEater.powers.InvisibleNoDrawPower;
import theTimeEater.util.OnChangeTempoSubscriber;

import static theTimeEater.util.Wiz.*;

public class EnterTempoAction extends AbstractGameAction {
    //consider renaming to "ChangeTempoAction"
    AbstractPlayer p = adp();
    DrawPilePanel drawPile = AbstractDungeon.overlayMenu.combatDeckPanel;
    DiscardPilePanel discardPile = AbstractDungeon.overlayMenu.discardPilePanel;
    tempos direction;

    //no argument means invert the current tempo
    public EnterTempoAction(){
        switch (TimeEaterMod.tempo) {
            case FORWARD:
                this.direction = tempos.REWIND;
                break;
            case REWIND:
                this.direction = tempos.FORWARD;
                break;
            case PAUSE:
                this.direction = tempos.PAUSE;
                break;
        }
    }

    public EnterTempoAction(tempos tempo) {
        this.direction = tempo;
    }

    @Override
    public void update() {
        //Don't do anything if trying to enter the tempo you're already in
        if (TimeEaterMod.tempo == this.direction) {
                isDone = true;
            return;
        }

        ArrayList<AbstractCard> allCards = getAllCardsInCombat();

        for (AbstractCard c : allCards) {
            if (c instanceof OnChangeTempoSubscriber) ((OnChangeTempoSubscriber) c).OnChangeTempo(this.direction);
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnChangeTempoSubscriber) {
                ((OnChangeTempoSubscriber) p).OnChangeTempo(this.direction);
            }
        }

        if (TimeEaterMod.tempo == tempos.PAUSE && adp().hasPower(InvisiblePausePower.POWER_ID)) {
            isDone = true;
            return;
        }

        if (TimeEaterMod.tempo == tempos.FORWARD && this.direction == tempos.PAUSE ||
            TimeEaterMod.tempo == tempos.PAUSE   && this.direction == tempos.FORWARD
        ){
            TimeEaterMod.tempo = this.direction;
            //we don't need to do anything to the piles if the switch is from forward to pause or vice versa
            isDone = true;
            return;
        }

        TimeEaterMod.tempo = this.direction;

        //flip the icons
        float temp_x = drawPile.target_x;
        float temp_y = drawPile.target_y;
        drawPile.target_x = drawPile.current_x = discardPile.target_x;
        drawPile.target_y = drawPile.current_y = discardPile.target_y;
        discardPile.target_x = discardPile.current_x = temp_x;
        discardPile.target_y = discardPile.current_y = temp_y;

        //change the contents
        AbstractPlayer p = adp();
        ArrayList<AbstractCard> tmp = p.discardPile.group;
        p.discardPile.group = p.drawPile.group;
        p.drawPile.group = tmp;

        isDone = true;
    }
}