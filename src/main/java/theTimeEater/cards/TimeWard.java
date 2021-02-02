package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeWardPower;

import static theTimeEater.TimeEaterMod.makeID;

public class TimeWard extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeWard.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public TimeWard() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeWardPower(p, 1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}