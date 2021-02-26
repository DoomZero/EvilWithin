package theTimeEater.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class SelectiveMemory extends AbstractTimeEaterCard {
    public final static String ID = makeID(SelectiveMemory.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SelectiveMemory() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new SelectCardsAction(p.drawPile.group, this.magicNumber, "Choose cards to discard", (cards) -> {
        atb(new SelectCardsAction(p.drawPile.group, 3, "Choose cards to discard", true, (c) -> true, (cards) -> {
            for (AbstractCard c : cards){
                att(new DiscardSpecificCardAction(c, p.drawPile));
            }
        }));
//        atb(new MoveCardsAction(p.discardPile, p.drawPile, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}