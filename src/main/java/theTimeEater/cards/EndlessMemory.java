package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class EndlessMemory extends AbstractTimeEaterCard {
    public final static String ID = makeID(EndlessMemory.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public EndlessMemory() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 7;
        this.returnToHand = true;
    }

    @Override
    public void update() {
        super.update();
        if (this.cost >= 1 && this.costForTurn >= 1) return;
        this.cost = this.costForTurn = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    public void upp() {
        upgradeDamage(3);
    }
}