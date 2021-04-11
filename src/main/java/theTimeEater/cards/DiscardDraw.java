package theTimeEater.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class DiscardDraw extends AbstractTimeEaterCard {
    public final static String ID = makeID(DiscardDraw.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public DiscardDraw() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractCard c = p.discardPile.getTopCard();
                att(new DiscardToHandAction(c));
                att(new AbstractGameAction() {
                    @Override
                    public void update() {
                        c.triggerWhenDrawn();
                        this.isDone = true;
                    }
                });
                this.isDone = true;
            }
        });
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}