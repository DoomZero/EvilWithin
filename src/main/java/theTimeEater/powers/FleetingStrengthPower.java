package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class FleetingStrengthPower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(FleetingStrengthPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static boolean triggered = false;

    public FleetingStrengthPower(AbstractCreature owner, int amount) {
        super(NAME, POWER_ID, PowerType.DEBUFF, owner, amount, false);
        loadRegion("time");
    }

    @Override
    public int onLoseHp(int damageAmount) {
        if (!triggered){
            atb(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -amount), -amount));
            this.remove();
            triggered = true;
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new FleetingStrengthPower(owner, this.amount);
    }
}
