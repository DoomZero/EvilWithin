package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ConservePower;
import theTimeEater.powers.EnergyBlurPower;

import static theTimeEater.TimeEaterMod.makeID;

public class DaylightSavings extends AbstractTimeEaterCard {
    public final static String ID = makeID(DaylightSavings.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public DaylightSavings() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ConservePower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}