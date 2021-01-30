package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class HoldYourBreath extends AbstractTimeEaterCard {
    public final static String ID = makeID(HoldYourBreath.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public HoldYourBreath() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TimeLockPower(p, 0));
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}