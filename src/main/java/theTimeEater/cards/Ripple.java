package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Ripple extends AbstractTimeEaterCard {
    public final static String ID = makeID(Ripple.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Ripple() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 10;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer unusedp, AbstractMonster unusedm) {
        blck();
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if ((!m.isDead) && (!m.isDying)) {
                applyToEnemy(m, new VulnerablePower(m, this.magicNumber, false));
                applyToEnemy(m, new WeakPower(m, this.magicNumber, false));
            }
        }
    }

    public void upp() {
        upgradeBlock(5);
        upgradeMagicNumber(1);
    }
}