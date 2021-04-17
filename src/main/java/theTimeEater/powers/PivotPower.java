package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TimeEaterMod;
import theTimeEater.util.OnChangeTempoSubscriber;

import static theTimeEater.util.Wiz.*;

public class PivotPower extends AbstractTimeEaterPower implements CloneablePowerInterface, OnChangeTempoSubscriber {
    public static final String POWER_ID = TimeEaterMod.makeID(PivotPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int triggersLeft = 0;

    public PivotPower(int amount) {
        this(adp(), amount);
    }

    public PivotPower(AbstractCreature owner, int amount) {
        super(NAME, POWER_ID, PowerType.BUFF, owner, amount, false);
        loadRegion("time");
        triggersLeft += amount;
    }

    @Override
    public void atStartOfTurn() {
        triggersLeft = amount;
    }

    @Override
    public void OnChangeTempo(TimeEaterMod.tempos tempo) {
        if (triggersLeft >= 1) {
            AbstractPlayer p = (AbstractPlayer) owner;
            p.gainEnergy(1);
            triggersLeft--;
        }
    }

    @Override
    public void stackPower(int amount) {
        this.fontScale = 8.0F;
        this.amount += amount;
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[3];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new PivotPower(owner, this.amount);
    }
}
