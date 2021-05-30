package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class WakeUpSlap extends AbstractTimeEaterCard {
    public final static String ID = makeID(WakeUpSlap.class.getSimpleName());
    // intellij stuff attack, enemy, common, 3, 3,  , , ,

    public WakeUpSlap() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.magicNumber = this.baseMagicNumber = 1;
        this.baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        //decrease duration of time lock on enemy
        TimeLockPower tl = (TimeLockPower) m.getPower(TimeLockPower.POWER_ID);
        if (tl == null) return;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                tl.changeDuration(-magicNumber);
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}