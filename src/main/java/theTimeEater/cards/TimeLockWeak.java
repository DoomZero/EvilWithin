package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class TimeLockWeak extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeLockWeak.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 3,  , , ,

    public TimeLockWeak() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (m.getPower(TimeLockPower.POWER_ID) != null) {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 2, false)));
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}