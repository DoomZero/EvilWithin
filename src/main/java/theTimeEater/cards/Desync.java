package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.DesynchronizePower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Desync extends AbstractTimeEaterCard {
    public final static String ID = makeID(Desync.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Desync() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DesynchronizePower(0));
        applyToSelf(new TimeLockPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}