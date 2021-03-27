package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeEaterWarpPower;

import static theTimeEater.TimeEaterMod.makeID;

public class TimeWarp extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeWarp.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public TimeWarp() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeEaterWarpPower(this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}