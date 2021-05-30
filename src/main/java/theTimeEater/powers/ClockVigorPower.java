package theTimeEater.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class ClockVigorPower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(ClockVigorPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int blockToGain = 4;
    private final int vigorToGain = 4;

    public ClockVigorPower() {
        super(NAME, POWER_ID, PowerType.BUFF, adp(), -1, false);
        loadRegion("time");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (!AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
//            gainBlock(blockToGain));
            applyToSelf(new VigorPower(adp(), vigorToGain));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new ClockVigorPower();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}