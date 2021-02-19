package theTimeEater.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class EnergyBlurPower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(EnergyBlurPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static Color myColor = new Color(0.710F, 1, 0.659F, 1);

    public EnergyBlurPower(final int amount) {
        super(NAME, POWER_ID, PowerType.BUFF, amount);
        loadRegion("time");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer){

    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new EnergyBlurPower(amount);
    }
}
