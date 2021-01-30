package awakenedOne.cards.cardvars;

import awakenedOne.cards.AbstractAwakenedCard;
import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static awakenedOne.AwakenedOneMod.makeID;

public class SillyVariable extends DynamicVariable {

    @Override
    public String key() {
        return makeID("si");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractAwakenedCard) {
            return ((AbstractAwakenedCard) card).isSillyModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractAwakenedCard) {
            return ((AbstractAwakenedCard) card).silly;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractAwakenedCard) {
            ((AbstractAwakenedCard) card).isSillyModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractAwakenedCard) {
            return ((AbstractAwakenedCard) card).baseSilly;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractAwakenedCard) {
            return ((AbstractAwakenedCard) card).upgradedSilly;
        }
        return false;
    }
}