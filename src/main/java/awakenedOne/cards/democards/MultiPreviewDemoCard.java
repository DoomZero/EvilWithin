package awakenedOne.cards.democards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.optionCards.BecomeAlmighty;
import com.megacrit.cardcrawl.cards.optionCards.FameAndFortune;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.tempCards.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import awakenedOne.cards.AbstractAwakenedCard;

import java.util.ArrayList;
import java.util.Collections;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.getRandomItem;
import static awakenedOne.util.Wiz.makeInHand;

public class MultiPreviewDemoCard extends AbstractAwakenedCard {

    public final static String ID = makeID(MultiPreviewDemoCard.class.getSimpleName());
    // intellij stuff skill, self, uncommon


    @Override
    protected float getRotationTimeNeeded() {
        return 0.33F;
    }

    private ArrayList<AbstractCard> myCardsList() {
        ArrayList<AbstractCard> bingle = new ArrayList<>();
        bingle.add(new Smite());
        bingle.add(new Safety());
        bingle.add(new Miracle());
        Expunger q = new Expunger();
        q.setX(2);
        bingle.add(q);
        bingle.add(new Insight());
        bingle.add(new BecomeAlmighty());
        bingle.add(new Beta());
        bingle.add(new Omega());
        bingle.add(new FameAndFortune());
        bingle.add(new ThroughViolence());
        bingle.add(new LiveForever());
        Collections.shuffle(bingle);
        return bingle;
    }

    public MultiPreviewDemoCard() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        isEthereal = true;
        cardToPreview = myCardsList();
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(getRandomItem(myCardsList()));
    }

    public void upp() {
        upgradeCardToPreview();
        uDesc();
    }
} 