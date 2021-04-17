package theTimeEater.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class ClockPower extends AbstractTimeEaterPower {
    public static final String POWER_ID = TimeEaterMod.makeID(ClockPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private final int blockToGain = 4;
    private final int vigorToGain = 4;
//    private int count = 0;

    public ClockPower() {
        super(NAME, POWER_ID, PowerType.BUFF, adp(), 1, false);
        loadRegion("time");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
//        if (!AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
//            gainBlock(blockToGain));
//            applyToSelf(new VigorPower(adp(), vigorToGain));
//        }

        switch ((amount - 1) / 3) {
            case 1:
                applyToSelf(new VigorPower(adp(), vigorToGain));
                break;
            case 2:
                atb(new DamageRandomEnemyAction(new DamageInfo(adp(), 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                break;
            case 3:
                gainBlock(blockToGain);
                break;
            case 4:
                break;
        }
        /*ClockPower thisPower = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                thisPower.amount++;
                if (thisPower.amount >= 12) thisPower.amount = 0;
            }
        });*/
        amount++;
        if (amount >= 13) amount = 1;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ClockPower();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
