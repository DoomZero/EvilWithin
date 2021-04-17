package theTimeEater.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TimeEaterMod;

import java.util.ArrayList;

import static theTimeEater.util.Wiz.*;

public class WarpFormPower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(WarpFormPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public ArrayList<AbstractPower> storedPowers = new ArrayList();

    public WarpFormPower(int amount) {
        super(NAME, POWER_ID, PowerType.BUFF, amount);
        loadRegion("time");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        flash();

    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new WarpFormPower(this.amount);
    }
}
