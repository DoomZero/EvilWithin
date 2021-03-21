package theTimeEater.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;

public class TimeEaterModalChoiceAction extends SelectCardsCenteredAction {

    //TODO: When StsLib SelectCardsAction gets the "centered" argument, swap to SelectCardsAction and use that argument.

    public TimeEaterModalChoiceAction(ArrayList<AbstractCard> list, int amount, String textforSelect) {
        super(list, amount, textforSelect, (cards) -> {
            for (AbstractCard q : cards) {
                q.onChoseThisOption();
            }
        });
    }

    public TimeEaterModalChoiceAction(ArrayList<AbstractCard> list, int amount) {
        this(list, amount, "Choose.");
    }

    public TimeEaterModalChoiceAction(ArrayList<AbstractCard> list) {
        this(list, 1, "Choose.");
    }
}
