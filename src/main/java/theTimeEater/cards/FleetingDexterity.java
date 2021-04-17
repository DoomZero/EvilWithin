package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theTimeEater.powers.FleetingDexterityPower;

import static theTimeEater.TimeEaterMod.makeID;

public class FleetingDexterity extends AbstractTimeEaterCard {
    public final static String ID = makeID(FleetingDexterity.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public FleetingDexterity() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, magicNumber));
        applyToSelf(new FleetingDexterityPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}