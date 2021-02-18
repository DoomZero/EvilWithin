package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AfterImagePower;

import static theTimeEater.TimeEaterMod.makeID;

public class AfterImage extends AbstractTimeEaterCard {
    public final static String ID = makeID(AfterImage.class.getSimpleName());
    // intellij stuff power, self, uncommon, 5, 3,  , , ,

    public AfterImage() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AfterImagePower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}