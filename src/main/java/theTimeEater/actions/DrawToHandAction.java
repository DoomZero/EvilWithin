package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DrawToHandAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final AbstractCard card;

    public DrawToHandAction(AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.card = card;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (p.drawPile.contains(this.card)) {
                if (p.hand.size() < 10) {
                    p.hand.addToHand(this.card);
                    this.card.unhover();
                    this.card.setAngle(0.0F, true);
                    this.card.lighten(false);
                    this.card.drawScale = 0.12F;
                    this.card.targetDrawScale = 0.75F;
                    this.card.applyPowers();
                    p.drawPile.removeCard(this.card);
                } else {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                }
            }

            p.hand.refreshHandLayout();
            p.hand.glowCheck();
        }

        this.tickDuration();
        this.isDone = true;
    }
}