package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.WarpFormPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class WarpForm extends AbstractTimeEaterCard {
    public final static String ID = makeID(WarpForm.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public WarpForm() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new WarpFormPower(1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}