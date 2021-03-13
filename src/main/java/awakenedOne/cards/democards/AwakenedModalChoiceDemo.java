package awakenedOne.cards.democards;

import awakenedOne.cards.AbstractAwakenedCard;
import awakenedOne.cards.AwakenedModalChoiceCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import awakenedOne.actions.EasyModalChoiceAction;

import java.util.ArrayList;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.*;

public class AwakenedModalChoiceDemo extends AbstractAwakenedCard {
    public final static String ID = makeID(AwakenedModalChoiceDemo.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , , 

    public AwakenedModalChoiceDemo() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSilly = silly = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> easyCardList = new ArrayList<>();
        easyCardList.add(new AwakenedModalChoiceCard("Draw", "Draw " + magicNumber + " cards.", () -> att(new DrawCardAction(magicNumber))));
        easyCardList.add(new AwakenedModalChoiceCard("Strength", "Gain " + silly + " Strength.", () -> applyToSelfTop(new StrengthPower(p, silly))));
        atb(new EasyModalChoiceAction(easyCardList));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSilly(1);
    }
}