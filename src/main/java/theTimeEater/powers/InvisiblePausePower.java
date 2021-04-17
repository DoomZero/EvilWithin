package theTimeEater.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.util.Wiz.*;

public class InvisiblePausePower extends AbstractTimeEaterPower implements NonStackablePower/*, InvisiblePower*/ {
    public static final String POWER_ID = TimeEaterMod.makeID(InvisiblePausePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InvisiblePausePower() {
        super(NAME, POWER_ID, PowerType.BUFF, AbstractDungeon.player);
        loadRegion("time");
    }

    @Override
    public void onInitialApplication() {
        applyToSelf(new InvisibleNoDrawPower());
        /*for(AbstractCard c : adp().hand.group){
            if (c.costForTurn == 0)
                c.setCostForTurn(1);
            if (c.costForTurn >= 1)
                c.returnToHand = true;
        }*/
    }

    @Override
    public boolean canPlayCard(AbstractCard c) {
        if (c.costForTurn == -1 && EnergyPanel.getCurrentEnergy() < 1) {
            c.cantUseMessage = "Can't play cost X cards in Pause Tempo!";
            return false;
        }
        return true;
    }

    @Override
    public void update(int slot) {
        super.update(slot);

        for (AbstractCard c : adp().hand.group) {
            if (c.costForTurn == 0)
                c.setCostForTurn(1);
            /*if (c.costForTurn >= 1 && !c.returnToHand)
                c.returnToHand = true;*/
        }
    }

    /*@Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.returnToHand) {
            card.returnToHand = true;
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            card.returnToHand = false;
                        }
                    });
                }
            });
        }
    }*/

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (!card.returnToHand) {
            card.returnToHand = true;
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    card.returnToHand = false;
                    isDone = true;
                }
            });
        }
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean unused) {
        /*ArrayList<AbstractCard> allCards = getAllCardsInCombat();
        for (AbstractCard c : allCards){
            if (!(c instanceof ReturnToHandInterface)) {
                c.returnToHand = false;
            }
        }*/
        atb(new EnterTempoAction(TimeEaterMod.tempos.FORWARD));
        this.remove();
    }

    @Override
    public AbstractPower makeCopy() {
        return new InvisiblePausePower();
    }
}
