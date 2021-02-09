package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;

public class RecurringRampage extends AbstractTimeEaterCard {
    public final static String ID = makeID(RecurringRampage.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public RecurringRampage() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 2;
        this.magicNumber = 5;
        this.returnToHand = true;
    }

    public void triggerWhenDrawn() {
        this.upgradeDamage(this.magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);

        updateCost(1);
        baseDamage *= 2;
    }

    public void upp() {
        upgradeDamage(3);
    }
}