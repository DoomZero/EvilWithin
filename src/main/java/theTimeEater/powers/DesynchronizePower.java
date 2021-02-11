package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TimeEaterMod;

public class DesynchronizePower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(DesynchronizePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DesynchronizePower(final int amount) {
        super(NAME, POWER_ID, PowerType.BUFF, amount);
        loadRegion("time");
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1){
            description = DESCRIPTIONS[0];
        }
        else {
            description = DESCRIPTIONS[1];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DesynchronizePower(0);
    }
}
