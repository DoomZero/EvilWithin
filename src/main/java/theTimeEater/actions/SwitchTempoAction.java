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
    static final DrawPilePanel drawPile = AbstractDungeon.overlayMenu.combatDeckPanel;
    static final DiscardPilePanel discardPile = AbstractDungeon.overlayMenu.discardPilePanel;

    static final float DRAW_DECK_X = 76.0F * Settings.scale - 64.0F;// 44
    static final float DRAW_DECK_Y = 74.0F * Settings.scale - 64.0F;// 45

    static final float DISCARD_DECK_X = 180.0F * Settings.scale - 64.0F;// 42
    static final float DISCARD_DECK_Y = 70.0F * Settings.scale - 64.0F;// 43

    static final float originalDrawPileX = drawPile.current_x;
    static final float originalDrawPileY = drawPile.current_y;
    static final float originalDiscardPileX = discardPile.current_x;
    static final float originalDiscardPileY = discardPile.current_y;


    @Override
    public void update() {
        //change the contents
        ArrayList<AbstractCard> tmp = p.discardPile.group;
        p.discardPile.group = p.drawPile.group;
        p.drawPile.group = tmp;

        //flip the icons
        if (p.tempo == tempos.REWIND){
            p.tempo = tempos.FORWARD;

            drawPile.target_x = drawPile.current_x = originalDrawPileX;
            drawPile.target_y = drawPile.current_y = originalDrawPileY;
            discardPile.target_x = discardPile.current_x = originalDiscardPileX;
            discardPile.target_y = discardPile.current_y = originalDiscardPileY;

        }
        if (p.tempo == tempos.FORWARD){
            p.tempo = tempos.REWIND;

//            drawPile.target_x = drawPile.current_x = originalDrawPileX + DECK_X * 2;
            drawPile.target_y = drawPile.current_y = originalDrawPileY;
//            discardPile.target_x = discardPile.current_x = originalDiscardPileX - DECK_X * 2;
            discardPile.target_y = discardPile.current_y = originalDiscardPileY;
        }

        isDone = true;
    }
}