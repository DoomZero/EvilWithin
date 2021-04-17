package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class SlugItOff extends AbstractTimeEaterCard {
    public final static String ID = makeID(SlugItOff.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 3,  , , ,

    public SlugItOff() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(this.magicNumber));
        TimeLockPower tl = (TimeLockPower) p.getPower(TimeLockPower.POWER_ID);
        if (tl != null && tl.getBaseDamage() > 0) {
            tl.increaseBaseDamage(-5);
        }
    }

    public void upp() {
        upgradeBlock(3);
//        upgradeMagicNumber(1);
    }
}