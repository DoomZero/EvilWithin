package theTimeEater.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class CarpeDiem extends AbstractTimeEaterCard {
    public final static String ID = makeID(CarpeDiem.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public CarpeDiem() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, "Keep.", (cards) -> {
            AbstractCard keepCard = cards.get(0);
            adp().hand.moveToHand(keepCard);
            cards.remove(keepCard);
            while (adp().hand.group.size() > 1) {
                AbstractCard c = adp().hand.getBottomCard();
                adp().hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(false);
            }
            adp().hand.refreshHandLayout();
        }));
        atb(new GainEnergyAction(4));
        applyToSelf(new NoDrawPower(p));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}