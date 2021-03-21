package theTimeEater.powers;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;

import static theTimeEater.TimeEaterMod.tempos;
import static theTimeEater.util.Wiz.*;

public class ReversePower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(ReversePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static Color myColor = new Color(0.710F, 1, 0.659F, 1);

    public ReversePower()
    {
        this(TimeEaterMod.tempos.FORWARD,1);
    }

    public ReversePower(int amount)
    {
        this(TimeEaterMod.tempos.FORWARD, amount);
    }

    public ReversePower(TimeEaterMod.tempos tempo, int amount){
        super(NAME, POWER_ID, PowerType.BUFF, amount);
        loadRegion("time");
    }

//    public void atStartOfTurn() {
//    }

    @Override
    public void stackPower(int stackAmount) {
//        this.fontScale = 8.0F;
        this.amount *= -1;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ReversePower(amount);
    }
}
