package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import theTimeEater.TheTimeEater;
import theTimeEater.TheTimeEater.tempos;
import theTimeEater.cards.DejaVu;
import theTimeEater.powers.PivotPower;
import theTimeEater.util.OnChangeTempoSubscriber;

import static theTimeEater.util.Wiz.atb;

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

        ArrayList<AbstractCard> allCards = new ArrayList<AbstractCard>();
        allCards.addAll(AbstractDungeon.player.drawPile.group);
        allCards.addAll(AbstractDungeon.player.discardPile.group);
        allCards.addAll(AbstractDungeon.player.hand.group);
        allCards.addAll(AbstractDungeon.player.exhaustPile.group);

        for (AbstractCard c : allCards) {
            if (c instanceof OnChangeTempoSubscriber) ((OnChangeTempoSubscriber) c).OnChangeTempo(this.direction);
        }

        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof OnChangeTempoSubscriber) ((OnChangeTempoSubscriber) p).OnChangeTempo(this.direction);
        }

        if (p.tempo == tempos.PAUSE) return;

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