package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.adp;
import static theTimeEater.util.Wiz.gainBlock;

public class InvisibleNoDrawPower extends NoDrawPower implements InvisiblePower, CloneablePowerInterface {
//    public static final String POWER_ID = TimeEaterMod.makeID(InvisibleNoDrawPower.class.getSimpleName());
    public static final String POWER_ID = "No Draw";
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int blockToGain = 4;

    public InvisibleNoDrawPower() {
        super(adp());
        this.type = PowerType.BUFF;
        loadRegion("time");
    }

    @Override
    public AbstractPower makeCopy() {
        return new InvisibleNoDrawPower();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
