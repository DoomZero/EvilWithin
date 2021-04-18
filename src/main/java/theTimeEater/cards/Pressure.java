package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.PressurePower;

import static theTimeEater.TimeEaterMod.makeID;

public class Pressure extends AbstractTimeEaterCard {
    public final static String ID = makeID(Pressure.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Pressure() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PressurePower(2, 2));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}