package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.actions.DiscardAllAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class DiscardAllAttack extends AbstractTimeEaterCard {
    public final static String ID = makeID(DiscardAllAttack.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DiscardAllAttack() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);

        atb(new DiscardAllAction(this));
    }

    public void upp() {
        upgradeDamage(3);
    }
}