package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class TimeShock extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeShock.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TimeShock() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower tl = m.getPower(TimeLockPower.POWER_ID);
        if (tl == null) return;

        applyToEnemy(m, new TimeLockPower(m, tl.amount));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}