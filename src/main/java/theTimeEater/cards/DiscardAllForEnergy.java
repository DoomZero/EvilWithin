package theTimeEater.cards;

import basemod.devcommands.hand.HandDiscard;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import javax.smartcardio.Card;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class DiscardAllForEnergy extends AbstractTimeEaterCard {
    public final static String ID = makeID(DiscardAllForEnergy.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public DiscardAllForEnergy() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
//        atb(new SelectCardsInHandAction(1, "Choose 1 card to keep.", (c) -> {
//            CardGroup temp = (CardGroup) p.hand.group.clone();
//            temp.removeCard(c.get(0));
//            temp.discardAll();
//        }));
        atb(new SelectCardsInHandAction(1, "keep.", (cards) -> {
            AbstractCard c = cards.get(0);
            adp().hand.removeCard(c);
            adp().limbo.addToTop(c);
            att(new DiscardAction(p, p, 20,true));
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    adp().limbo.moveToHand(c);
                }
            });
//            for(AbstractCard card: adp().hand.group){
//                adp().hand.moveToDiscardPile(card);
//                card.triggerOnManualDiscard();
//                GameActionManager.incrementDiscard(false);
//
//            }
//            CardGroup hand = new CardGroup(realHand, CardGroup.CardGroupType.UNSPECIFIED);
//            for(AbstractCard card: hand.group){
//                if(!card.equals(c)) {
//                    realHand.moveToDiscardPile(card);
//                    card.triggerOnManualDiscard();
//                    GameActionManager.incrementDiscard(false);
//                }
//            }
        }));
        atb(new GainEnergyAction(4));
//        applyToSelf(new NoDrawPower(p));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}