package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theTimeEater.powers.FleetingStrengthPower;
import theTimeEater.powers.IntangibleNextTurnPower;
import theTimeEater.powers.TakeDoubleDamagePower;

import static theTimeEater.TimeEaterMod.makeID;

public class FleetingStrength extends AbstractTimeEaterCard {
    public final static String ID = makeID(FleetingStrength.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public FleetingStrength() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new FleetingStrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}