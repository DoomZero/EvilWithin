package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;

public class EndlessMemory extends AbstractTimeEaterCard {
    public final static String ID = makeID(EndlessMemory.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public EndlessMemory() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        this.baseDamage = 6;
        this.returnToHand = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(3);
    }
}