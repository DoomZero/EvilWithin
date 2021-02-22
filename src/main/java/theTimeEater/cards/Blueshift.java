package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.IntangibleNextTurnPower;
import theTimeEater.powers.TakeDoubleDamagePower;

import static theTimeEater.TimeEaterMod.makeID;

public class Blueshift extends AbstractTimeEaterCard {
    public final static String ID = makeID(Blueshift.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Blueshift() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TakeDoubleDamagePower(p, 1));
        applyToSelf(new IntangibleNextTurnPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}