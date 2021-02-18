package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class RecurringStrike extends AbstractTimeEaterCard {
    public final static String ID = makeID(RecurringStrike.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public RecurringStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        this.baseDamage = 5;
        this.returnToHand = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);

        AbstractCard r = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                r.updateCost(1);
            }
        });
        baseDamage *= 2;
    }

    public void upp() {
        upgradeDamage(3);
    }
}