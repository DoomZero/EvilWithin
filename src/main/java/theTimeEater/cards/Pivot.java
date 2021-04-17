package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.PivotPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Pivot extends AbstractTimeEaterCard {
    public final static String ID = makeID(Pivot.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Pivot() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PivotPower(1));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}