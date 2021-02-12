package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.DiscardPilePanel;
import com.megacrit.cardcrawl.ui.panels.DrawPilePanel;
import theTimeEater.TheTimeEater;
import theTimeEater.TheTimeEater.tempos;

import java.util.ArrayList;

public class SwitchTempoAction extends AbstractGameAction {

    TheTimeEater p = (TheTimeEater) AbstractDungeon.player;
    DrawPilePanel drawPile = AbstractDungeon.overlayMenu.combatDeckPanel;
    DiscardPilePanel discardPile = AbstractDungeon.overlayMenu.discardPilePanel;

//    public SwitchTempoAction(AbstractPlayer p) { }

    @Override
    public void update() {
        //change the contents
        ArrayList<AbstractCard> tmp = p.discardPile.group;
        p.discardPile.group = p.drawPile.group;
        p.drawPile.group = tmp;

        if (p.tempo == tempos.REWIND)  p.tempo = tempos.FORWARD;
        if (p.tempo == tempos.FORWARD) p.tempo = tempos.REWIND;

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