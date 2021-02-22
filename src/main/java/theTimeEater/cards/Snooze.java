package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Snooze extends AbstractTimeEaterCard {
    public final static String ID = makeID(Snooze.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Snooze() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        TimeLockPower tl = (TimeLockPower) p.getPower(TimeLockPower.POWER_ID);
        if (tl != null) {
            tl.changeDuration(1);
        }
        else {
            applyToSelf(new TimeLockPower(p, this.magicNumber));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}