package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import theTimeEater.TimeEaterMod;

public class TakeDoubleDamagePower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(TakeDoubleDamagePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = true;

    public TakeDoubleDamagePower(AbstractCreature owner, int duration) {
        super(NAME, POWER_ID, PowerType.DEBUFF, true, owner, duration);
        loadRegion("time");
    }

    @Override
    public void stackPower(int amount) {
        this.fontScale = 8.0F;
        this.amount += amount;
    }

    @Override
    public float atDamageReceive(float damage, DamageInfo.DamageType damageType) {
        damage *= 2;
        return super.atDamageReceive(damage, damageType);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        decrement();
    }// 38

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + DESCRIPTIONS[1];
        else
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TakeDoubleDamagePower(owner, this.amount);
    }
}
