package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class RecordScratch extends AbstractTimeEaterCard {
    public final static String ID = makeID(RecordScratch.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 3,  , , ,

    public RecordScratch() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToEnemy(m, new WeakPower(m, baseMagicNumber, false));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }
}