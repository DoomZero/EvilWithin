package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.ButterflyEffectPower;
import theTimeEater.powers.DesynchronizePower;
import theTimeEater.powers.TimeLockExtendablePower;

import static theTimeEater.TimeEaterMod.makeID;

public class ButterflyEffect extends AbstractTimeEaterCard {
    public final static String ID = makeID(ButterflyEffect.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public ButterflyEffect() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ButterflyEffectPower());
    }

    public void upp() {
        upgradeBlock(3);
    }
}