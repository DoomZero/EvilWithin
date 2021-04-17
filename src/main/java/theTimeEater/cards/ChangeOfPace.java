package theTimeEater.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;
import theTimeEater.actions.TimeEaterModalChoiceAction;

import java.util.ArrayList;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class ChangeOfPace extends AbstractTimeEaterCard {
    public final static String ID = makeID(ChangeOfPace.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public ChangeOfPace() {
        super(ID, 0, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public static Runnable forward = () -> {
        att(new EnterTempoAction(TimeEaterMod.tempos.FORWARD));
    };

    public static Runnable rewind = () -> {
        att(new EnterTempoAction(TimeEaterMod.tempos.REWIND));
    };

    public static Runnable doNothing = () -> {
    };

    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choiceList = new ArrayList<>();
        choiceList.add(new TimeEaterModalChoiceCard("Rewind", cardStrings.EXTENDED_DESCRIPTION[0], rewind));
        choiceList.add(new TimeEaterModalChoiceCard("Nothing", cardStrings.EXTENDED_DESCRIPTION[1], doNothing));
        choiceList.add(new TimeEaterModalChoiceCard("Forward", cardStrings.EXTENDED_DESCRIPTION[2], forward));
        atb(new TimeEaterModalChoiceAction(choiceList));

        scry();
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}