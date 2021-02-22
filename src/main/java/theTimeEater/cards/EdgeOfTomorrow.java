package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class EdgeOfTomorrow extends AbstractTimeEaterCard {
    public final static String ID = makeID(EdgeOfTomorrow.class.getSimpleName());
    // intellij stuff attack, enemy, common, 3, 3,  , , ,

    public EdgeOfTomorrow() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //detonate time lock on enemy
        TimeLockPower tl = (TimeLockPower) m.getPower(TimeLockPower.POWER_ID);
        if (tl == null) return;

        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                tl.explode();
            }
        });

        //reapply time lock to enemy
        if (!m.isDeadOrEscaped()) applyToEnemy(m, new TimeLockPower(m, tl.amount, tl.amount2));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}