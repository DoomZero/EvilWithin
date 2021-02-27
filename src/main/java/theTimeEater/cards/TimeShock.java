package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class TimeShock extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeShock.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TimeShock() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        TimeLockPower tl = (TimeLockPower) m.getPower(TimeLockPower.POWER_ID);
        if (tl == null) return;

        tl.setBaseDamage(tl.getBaseDamage()*2);
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}