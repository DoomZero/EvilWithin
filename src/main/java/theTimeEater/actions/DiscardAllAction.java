package theTimeEater.actions;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static theTimeEater.util.Wiz.*;

public class DiscardAllAction extends AbstractTimeEaterAction {

    final AbstractPlayer p = AbstractDungeon.player;
    final AbstractCard c;

    public DiscardAllAction(AbstractCard c) {
        this.c = c;
    }

    @Override
    public void update() {
        if (!p.drawPile.contains(c)) {
            isDone = true;
            return;
        }
        for (AbstractCard card : p.drawPile.group){
            att(new DiscardSpecificCardAction(card, p.drawPile));
        }
        isDone = true;
    }
}