package awakenedOne.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.*;

public class WingedRiposte extends AbstractAwakenedCard {
    public final static String ID = makeID("WingedRiposte");
    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 2;

    public WingedRiposte() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = MAGIC;
        tags.add(AWAKENED);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (isAwakened)
            for(int i = 0; i < magicNumber; ++i) {
                this.addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            }
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }
}