package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class Recall extends AbstractTimeEaterCard {
    public final static String ID = makeID(Recall.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Recall() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new BetterDiscardPileToHandAction(this.magicNumber));
        atb(new DiscardAction(p,p,this.magicNumber,false));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}