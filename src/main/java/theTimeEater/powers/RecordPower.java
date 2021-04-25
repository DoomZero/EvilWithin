package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.atb;

public class RecordPower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(RecordPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private int hp;

    public RecordPower(AbstractCreature owner) {
        super(NAME, POWER_ID, PowerType.BUFF, owner, -1, false);
        loadRegion("time");
        this.hp = owner.currentHealth;
        this.updateDescription();
    }

    public void setHP(){
        RecordPower thisPower = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                thisPower.owner.currentHealth = thisPower.hp;
                thisPower.owner.healthBarUpdatedEvent();
                thisPower.removeTop();
            }
        });
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.hp + LocalizedStrings.PERIOD;
    }

    @Override
    public AbstractPower makeCopy() {
        return new RecordPower(this.owner);
    }
}
