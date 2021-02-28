package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeWardPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Haste extends AbstractTimeEaterCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Haste() {
        super(ID, 2, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeWardPower(1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}