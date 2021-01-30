package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class WakeUpSlap extends AbstractTimeEaterCard {
    public final static String ID = makeID(WakeUpSlap.class.getSimpleName());
    // intellij stuff attack, enemy, common, 3, 3,  , , ,

    public WakeUpSlap() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        //decrease duration of time lock on enemy
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m.hasPower(TimeLockPower.POWER_ID)) {
                    TimeLockPower tl = (TimeLockPower) m.getPower(TimeLockPower.POWER_ID);
                    tl.setDuration(tl.amount2 - 1);
                    if (tl.amount2 <= 0){
                        tl.explode();
                    }
                }
            }
        });
    }

    public void upp() {
        upgradeDamage(3);
    }
}