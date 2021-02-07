package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.actions.FlipTempoAction;
import theTimeEater.powers.ReversePower;
import theTimeEater.powers.TimeLockExtendablePower;

import static theTimeEater.TimeEaterMod.makeID;

public class OneTimeRewind extends AbstractTimeEaterCard {
    public final static String ID = makeID(OneTimeRewind.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public OneTimeRewind()  {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.returnToHand = true;
        this.selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new FlipTempoAction());
        applyToSelf(new ReversePower());
//        this.addToBot(new MoveDrawAndDiscardPileAction());
        this.addToBot(new DrawCardAction(p, 1));
    }

    public void upp() {
        upgradeBlock(3);
    }
}