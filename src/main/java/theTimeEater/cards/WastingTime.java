package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class WastingTime extends AbstractTimeEaterCard {
    public final static String ID = makeID(WastingTime.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WastingTime() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}