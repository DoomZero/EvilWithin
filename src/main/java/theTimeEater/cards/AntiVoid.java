package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class AntiVoid extends AbstractTimeEaterCard {
    public final static String ID = makeID(AntiVoid.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public AntiVoid() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.isEthereal = true;
        this.baseMagicNumber = this.magicNumber = 1;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new GainEnergyAction(this.magicNumber));
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}