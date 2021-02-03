package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theHexaghost.vfx.ExplosionSmallEffectGreen;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.atb;

public class TimeLockExtendablePower extends TwoAmountPower implements CloneablePowerInterface, HealthBarRenderPower {
    public static final String POWER_ID = TimeEaterMod.makeID(TimeLockExtendablePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = true;

    public static Color myColor = new Color(0.710F, 1, 0.659F, 1);

    public TimeLockExtendablePower(AbstractCreature owner, int amount) {
        this(owner, amount, 1);
    }

    public TimeLockExtendablePower(AbstractCreature owner, int amount, int duration) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = true;
        amount2 = duration;
        this.type = PowerType.BUFF;
        this.updateDescription();
        loadRegion("time");
    }

    @Override
    public int getHealthBarAmount() {
        if (amount2 == 1)
            return this.amount;
        return 0;
    }

    @Override
    public Color getColor() {
        return myColor.cpy();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
            this.stackPower(damageAmount);
            this.flash();
        }

        return 0;
    }

    @Override
    public void atStartOfTurn() {
        if (owner.isPlayer) return;
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) {// 65 66
            explode();
        } else {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    amount2--;
                    updateDescription();
                }
            });
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (!owner.isPlayer) return;
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) {// 65 66
            explode();
        } else {
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    amount2--;
                    updateDescription();
                }
            });
        }
    }

    public void explode(){
        this.flashWithoutSound();
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        if (this.amount > 0){
            atb(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
//            this.addToBot(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.FIRE));
            atb(new DamageAction(owner, new DamageInfo(AbstractDungeon.player, amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    public void setDuration(int durAmount){
        this.fontScale = 8.0F;
        amount2 = durAmount;
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void updateDescription() {
        if (amount2 == 1)
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[3];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TimeLockExtendablePower(owner, this.amount);
    }
}
