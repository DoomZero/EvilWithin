package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theHexaghost.vfx.ExplosionSmallEffectGreen;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class TimeLockPower extends AbstractTimeEaterPower implements CloneablePowerInterface, HealthBarRenderPower, OnReceivePowerPower {
    public static final String POWER_ID = TimeEaterMod.makeID(TimeLockPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean justApplied = false;
    private int baseDamage = 0;

    public static Color myColor = new Color(0.710F, 1, 0.659F, 1);
//    public static Color myColor = TimeEaterMod.characterColor;

    public TimeLockPower(AbstractCreature owner, int duration) {
        this(owner, 0, duration);
    }

    public TimeLockPower(AbstractCreature owner, int damage, int duration) {
        super(NAME, POWER_ID, PowerType.BUFF, owner, damage, duration,true);
        baseDamage = damage;
        loadRegion("time");
        if (owner == adp()) justApplied = true;
    }

    @Override
    public int getHealthBarAmount() {
        if (amount2 == 1)
            return amount;
        return 0;
    }

    @Override
    public Color getColor() {
        return myColor.cpy();
    }

    @Override
    public int onAttackedToChangeDamage(DamageInfo info, int damageAmount) {
        if (damageAmount > 0) {
//            this.stackDamage(info.base);
            this.stackDamage(damageAmount);
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
    public void atEndOfTurn(boolean unused) {
        if (!owner.isPlayer) return;
        if (this.justApplied) {
            this.justApplied = false;
            return;
        }
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead() && amount2 == 1) {// 65 66
            explode();

            //reapply Time Lock after explosion if player has Desynchronize power
            if (this.owner.hasPower(DesynchronizePower.POWER_ID)){
                atb(new ApplyPowerAction(this.owner, this.owner, new TimeLockPower(this.owner, 1)));
            }

        } else {
            //Reduce the turn counter by 1
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
        //note: this is also called in TimeEaterMod.java to facilitate end of combat explosion
        this.flashWithoutSound();
        this.remove();

        DamageInfo d = new DamageInfo(adp(), baseDamage);
        d.applyEnemyPowersOnly(owner);
        amount = d.output;

        if (amount > 0){
            atb(new VFXAction(new ExplosionSmallEffectGreen(this.owner.hb.cX, this.owner.hb.cY), 0.1F));
//            this.addToBot(new LoseHPAction(owner, owner, amount, AbstractGameAction.AttackEffect.FIRE));
            if (AbstractDungeon.player.hasPower(ButterflyEffectPower.POWER_ID)){
                for (AbstractMonster m: AbstractDungeon.getCurrRoom().monsters.monsters){
                    if (!m.isDeadOrEscaped()){
                        atb(new DamageAction(m, new DamageInfo(m, amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
                    }
                }
            } else {
                atb(new DamageAction(owner, new DamageInfo(owner, amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public void setDuration(int durAmount){
        this.fontScale = 8.0F;
        amount2 = durAmount;
    }

    public void stackDamage(int damageAmount){
        this.fontScale = 8.0F;
        baseDamage += damageAmount;
        updateDamage();
    }

    @Override
    public void stackPower(int amount) {
        this.fontScale = 8.0F;
        amount2 += amount;
    }

    @Override
    public boolean onReceivePower(AbstractPower pow, AbstractCreature target, AbstractCreature source){
        //skip non-owner targets
        if (target != owner) return true;

        updateDamage();

        return true;
    }

    public void updateDamage(){
        AbstractPower p = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                DamageInfo d = new DamageInfo(adp(), baseDamage);
                d.applyEnemyPowersOnly(owner);
                p.amount = d.output;
                p.updateDescription();
                isDone = true;
            }
        });
    }

    @Override
    public void updateDescription() {
        final String PLURAL_BIT;
        if (amount2 == 1)
            PLURAL_BIT = DESCRIPTIONS[2];
        else
            PLURAL_BIT = DESCRIPTIONS[1];

        description = DESCRIPTIONS[0] + amount2 + PLURAL_BIT + amount + DESCRIPTIONS[3] + baseDamage + DESCRIPTIONS[4];
    }

    @Override
    public AbstractPower makeCopy() {
        return new TimeLockPower(owner, amount, amount2);
    }
}
