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
//        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeLockPower(p, 1));
        TimeLockPower tl = (TimeLockPower) p.getPower(TimeLockPower.POWER_ID);
        if (tl != null && tl.getBaseDamage() > 0){
            tl.setBaseDamage(-10);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}