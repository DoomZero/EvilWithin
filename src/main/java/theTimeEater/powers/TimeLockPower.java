package theTimeEater.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class TimeLockPower extends AbstractTimeEaterPower implements HealthBarRenderPower, OnReceivePowerPower {
    public static final String POWER_ID = TimeEaterMod.makeID(TimeLockPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    private int baseDamage = 0;
    private boolean exploding = false;

    public static Color normalColor = new Color(0.710F, 1, 0.659F, 1);
    public static Color explodeColor = new Color(0.659F, 1, 1, 1);
//    public static Color myColor = TimeEaterMod.characterColor;

    public TimeLockPower(AbstractCreature owner, int duration) {
        this(owner, duration, 0);
    }

    public TimeLockPower(AbstractCreature owner, int duration, int damage) {
        super(NAME, POWER_ID, PowerType.BUFF, owner, duration, damage, true);
        increaseBaseDamage(damage);
        loadRegion("time");
        if (owner == adp()) justApplied = true;

        updateDescription();
    }

    @Override
    public int getHealthBarAmount() {
        return amount2;
    }

    @Override
    public Color getColor() {
        if (amount <= 1 || exploding)
            return explodeColor.cpy();
        return normalColor.cpy();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        //lets damage through if the damage is the explosion itself
        if (exploding) return damageAmount;

        if (damageAmount > 0) {
//            this.stackDamage(info.base);
            this.increaseBaseDamage(damageAmount);
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
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount <= 1) {// 65 66
            explode();
        } else {
            AbstractPower p = this;
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    p.amount--;
                    updateDescription();
                }
            });
        }
    }

    @Override
    public void atEndOfTurn(boolean unused) {
        if (!owner.isPlayer) return;
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount <= 1) {// 65 66
            explode();
        } else {
            //Reduce the turn counter by 1
            AbstractPower p = this;
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    p.amount--;
                    updateDescription();
                }
            });
        }
    }

    public void explode() {
        //note: this is also called in TimeEaterMod.java to facilitate end of combat explosion
        this.flashWithoutSound();
        exploding = true;

        DamageInfo d = new DamageInfo(adp(), baseDamage);
        d.applyEnemyPowersOnly(owner);
        amount2 = d.output;

        if (amount2 > 0) {
//            actionList.add(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));

            if (AbstractDungeon.player.hasPower(ButterflyEffectPower.POWER_ID)) {
                for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!m.isDeadOrEscaped()) {
                        att(new DamageAction(m, new DamageInfo(m, amount2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                    }
                }
            } else {
                att(new DamageAction(owner, new DamageInfo(owner, amount2, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }

        att(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onRemove() {
        //reapply Time Lock after explosion if player has Desync power
        if (owner.hasPower(DesynchronizePower.POWER_ID) && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            atb(new ApplyPowerAction(this.owner, this.owner, new TimeLockPower(this.owner, 1)));
        }
    }

    public void changeDuration(int changeAmount) {
        setDuration(amount + changeAmount);
    }

    public void setDuration(int durAmount) {
        this.fontScale = 8.0F;
        amount = durAmount;
        if (amount <= 0) {
            explode();
        }
    }

    public int getBaseDamage() {
        return this.baseDamage;
    }

    public void setBaseDamage(int damageAmount) {
        this.fontScale = 8.0F;
        baseDamage = damageAmount;
        if (baseDamage < 0) baseDamage = 0;
        updateDamage();
    }

    public void increaseBaseDamage(int damageAmount) {
        setBaseDamage(damageAmount + baseDamage);
    }

    @Override
    public void stackPower(int amount) {
        this.fontScale = 8.0F;
        if (!exploding) this.amount += amount;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source) {
        //skip non-owner targets
        if (target != owner) return true;

        updateDamage();

        return true;
    }

    public void updateDamage() {
        DamageInfo d = new DamageInfo(adp(), baseDamage);
        d.applyEnemyPowersOnly(owner);
        amount2 = d.output;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        final String PLURAL_BIT;
        if (amount == 1)
            PLURAL_BIT = DESCRIPTIONS[1];
        else
            PLURAL_BIT = DESCRIPTIONS[2];

        description = DESCRIPTIONS[0] + amount + PLURAL_BIT + amount2 + DESCRIPTIONS[3] + baseDamage + DESCRIPTIONS[4];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TimeLockPower(owner, amount2, amount);
    }
}
