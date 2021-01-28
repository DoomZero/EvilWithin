package theTimeEater.cards.cardvars;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theTimeEater.cards.AbstractTimeEaterCard;

import static theTimeEater.TimeEaterMod.makeID;

public class SillyVariable extends DynamicVariable {

    @Override
    public String key() {
        return makeID("si");
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractTimeEaterCard) {
            return ((AbstractTimeEaterCard) card).isSillyModified;
        }
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractTimeEaterCard) {
            return ((AbstractTimeEaterCard) card).silly;
        }
        return -1;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractTimeEaterCard) {
            ((AbstractTimeEaterCard) card).isSillyModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractTimeEaterCard) {
            return ((AbstractTimeEaterCard) card).baseSilly;
        }
        return -1;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractTimeEaterCard) {
            return ((AbstractTimeEaterCard) card).upgradedSilly;
        }
        return false;
    }
}