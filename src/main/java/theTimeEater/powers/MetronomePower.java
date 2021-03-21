package theTimeEater.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;
import theTimeEater.actions.TimeEaterModalChoiceAction;
import theTimeEater.cards.TimeEaterModalChoiceCard;

import java.util.ArrayList;
import java.util.Arrays;

import static theTimeEater.util.Wiz.*;

public class MetronomePower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(MetronomePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MetronomePower() {
        super(NAME, POWER_ID, PowerType.BUFF, adp(), -1, false);
        loadRegion("time");
    }

    public static ArrayList<Runnable> effects = new ArrayList<Runnable>(Arrays.asList(
            () -> att(new EnterTempoAction(TimeEaterMod.tempos.REWIND)),
            () -> {},
            () -> att(new EnterTempoAction(TimeEaterMod.tempos.FORWARD))
    ));

    @Override
    public void atStartOfTurn(){
        ArrayList<AbstractCard> choiceList = new ArrayList<>();
        choiceList.add(new TimeEaterModalChoiceCard("Rewind",  DESCRIPTIONS[1], effects.get(0)));
        choiceList.add(new TimeEaterModalChoiceCard("Nothing", DESCRIPTIONS[2], effects.get(1)));
        choiceList.add(new TimeEaterModalChoiceCard("Forward", DESCRIPTIONS[3], effects.get(2)));
        atb(new TimeEaterModalChoiceAction(choiceList));
    }

    @Override
    public AbstractPower makeCopy() {
        return new MetronomePower();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
