package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.DrawVoidPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class BorrowedTime extends AbstractTimeEaterCard {
    public final static String ID = makeID(BorrowedTime.class.getSimpleName());
    //stupid intellij stuff skill, self, uncommon

    public BorrowedTime() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        this.cardsToPreview = new VoidCard();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(magicNumber));
        applyToSelf(new DrawVoidPower(p, 1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}