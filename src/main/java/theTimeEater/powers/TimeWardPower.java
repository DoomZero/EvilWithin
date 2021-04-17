package theTimeEater.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theTimeEater.TimeEaterMod;

import java.util.ArrayList;

public class TimeWardPower extends AbstractTimeEaterPower implements OnReceivePowerPower {
    public static final String POWER_ID = TimeEaterMod.makeID(TimeWardPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public static Color myColor = new Color(0.710F, 1, 0.659F, 1);

    public ArrayList<AbstractPower> storedPowers = new ArrayList();

    public TimeWardPower(int duration) {
        super(NAME, POWER_ID, PowerType.BUFF, duration);
        this.isTurnBased = true;
        loadRegion("time");
    }


    @Override
    public int onReceivePowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stackAmount) {
        // must be applied to player, must be debuff
        if (target != owner || power.type == PowerType.BUFF) return stackAmount;

        storedPowers.add(power);
        this.flash();

        return 0;
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        // incoming power must be applied to player, must be debuff
        if (target != owner || power.type == PowerType.BUFF) return true;

        storedPowers.add(power);
        this.flash();

        return false;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!owner.isPlayer) return;
        if ((this.amount <= 1) && (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            explode();
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, this, 1));
            updateDescription();
        }
    }

    public void explode() {
        this.flashWithoutSound();
//        this.addToBot(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));

        for (AbstractPower storedPower : storedPowers) {
            this.addToBot(new ApplyPowerAction(owner, owner, storedPower, storedPower.amount));
        }
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TimeWardPower(this.amount);
    }
}
