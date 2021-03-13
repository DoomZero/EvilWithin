package awakenedOne.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.*;

public class Sludge extends AbstractAwakenedCard {
    public final static String ID = makeID(Sludge.class.getSimpleName());
    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 5;

    private static final int MAGIC = 2;

    public Sludge() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, autoWeak(m, magicNumber));
        applyToEnemy(m, autoVuln(m, magicNumber));
        shuffleIn(new VoidCard());
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}