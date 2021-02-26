package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class OneUseTimeLock extends AbstractTimeEaterCard {
    public final static String ID = makeID(OneUseTimeLock.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public OneUseTimeLock() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeLockPower(p, 1));
    }

    public void upp() {
        this.exhaust = false;
        uDesc();
    }
}