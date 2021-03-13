package awakenedOne.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.*;

public class ShieldingStrike extends AbstractAwakenedCard {
    public final static String ID = makeID(ShieldingStrike.class.getSimpleName());
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;

    public ShieldingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        tags.add(AWAKENED);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isAwakened)
            blck();
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}