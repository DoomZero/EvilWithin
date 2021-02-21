package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.powers.IntangiblePower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class IntangibleNextTurnPower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(IntangibleNextTurnPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
//    private boolean justApplied = true;

    public IntangibleNextTurnPower(AbstractCreature owner, int duration) {
        super(NAME, POWER_ID, PowerType.BUFF, owner, duration, true);
        loadRegion("time");
    }

    @Override
    public void stackPower(int amount) {
        this.fontScale = 8.0F;
        this.amount += amount;
    }

    @Override
    public void atStartOfTurn() {
        this.flash();// 35
        if (owner == adp()){
            applyToSelf(new IntangiblePlayerPower(this.owner, 1));
        }
        else {
            applyToEnemy((AbstractMonster) this.owner, new IntangiblePower(this.owner, 1));
        }
        decrement();
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0] + "#b"+amount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new IntangibleNextTurnPower(owner, this.amount);
    }
}
