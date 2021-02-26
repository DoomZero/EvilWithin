package theTimeEater.cards;

import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BlurPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class BlurCard extends AbstractTimeEaterCard {
    public final static String ID = makeID(BlurCard.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public BlurCard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 5;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, 1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}