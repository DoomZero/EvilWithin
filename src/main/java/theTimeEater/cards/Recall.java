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
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new BetterDiscardPileToHandAction(1));
        atb(new DiscardAction(p,p,1,false));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}