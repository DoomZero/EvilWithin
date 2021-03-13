package awakenedOne.cards;

import automaton.actions.EasyXCostAction;
import awakenedOne.powers.AwakenedHexPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.applyToEnemy;
import static awakenedOne.util.Wiz.atb;

public class EnervatingAssault extends AbstractAwakenedCard {
    public final static String ID = makeID(EnervatingAssault.class.getSimpleName());
    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;
    private static final int MAGIC = 1;

    public EnervatingAssault() {
        super(ID, -1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect; i++) {
                dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
                applyToEnemy(m, new AwakenedHexPower(m, baseMagicNumber));
            }
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}