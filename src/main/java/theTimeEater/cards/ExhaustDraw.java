package theTimeEater.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class ExhaustDraw extends AbstractTimeEaterCard {
    public final static String ID = makeID(ExhaustDraw.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExhaustDraw() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //if upgraded, may choose zero
        atb(new SelectCardsInHandAction(this.magicNumber, "Exhaust.", true, true, (c) -> true, (cards) -> {
            if (cards.size() == 0) return;
            int cardDraw = 1;
            for (AbstractCard c : cards){
                if (c.cost >= 0) cardDraw += c.cost;
                else if (c.cost == -1) cardDraw += adp().energy.energy;
                adp().hand.moveToExhaustPile(c);
                c.exhaustOnUseOnce = false;
                c.freeToPlayOnce = false;
            }
            att(new DrawCardAction(cardDraw));
            cards.removeAll(cards);
        }));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}