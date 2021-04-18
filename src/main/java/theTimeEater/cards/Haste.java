package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theTimeEater.powers.PressurePower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class Haste extends AbstractTimeEaterCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Haste() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new RemoveDebuffsAction(p));
        applyToSelf(new RegenPower(p,this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}