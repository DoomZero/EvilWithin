package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;

public class TimelineDivergence extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimelineDivergence.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public TimelineDivergence() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        scry();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                p.drawPile.shuffle();
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}