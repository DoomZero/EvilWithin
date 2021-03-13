package awakenedOne.cards.democards;

import awakenedOne.cards.AbstractAwakenedCard;
import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.cardmods.EtherealMod;
import downfall.cardmods.ExhaustMod;

import java.util.ArrayList;
import java.util.Collections;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.*;

public class SelectCardsPlusCardMods extends AbstractAwakenedCard {

    public final static String ID = makeID(SelectCardsPlusCardMods.class.getSimpleName());
    // intellij stuff skill, self, uncommon

    public SelectCardsPlusCardMods() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> myCardsList = new ArrayList<>();
        ArrayList<AbstractCard> eligibleCardsList = getCardsMatchingPredicate(c -> c.cost == 0, true);
        Collections.shuffle(eligibleCardsList);
        for (int i = 0; i < 3; i++) {
            CardModifierManager.addModifier(eligibleCardsList.get(i), new EtherealMod());
            CardModifierManager.addModifier(eligibleCardsList.get(i), new ExhaustMod());
            myCardsList.add(eligibleCardsList.get(i));
        }
        atb(new SelectCardsAction(myCardsList, 1, "Choose a card to add into your hand with Ethereal and Exhaust.", (cards) -> {
            att(new MakeTempCardInHandAction(cards.get(0), 1, true));
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
} 