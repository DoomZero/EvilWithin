package awakenedOne.powers;


import awakenedOne.AwakenedOneMod;
import awakenedOne.stances.AwakenedPhase;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.BlurPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import guardian.stances.DefensiveMode;

import static awakenedOne.util.Wiz.atb;


public class RitePower extends AbstractAwakenedPower {
    public static final String POWER_ID = AwakenedOneMod.makeID("RitePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int BLOCKONTRIGGER = 10;
    public static final PowerType POWER_TYPE = PowerType.BUFF;
    private AbstractCreature source;
    private boolean active;
    private int amount_gain = 1;

    public RitePower(AbstractCreature owner, AbstractCreature source) {
        super("Rite", POWER_TYPE, false, owner, 0);

        this.ID = POWER_ID;
        this.owner = owner;
        this.source = source;
        this.type = POWER_TYPE;
        this.amount = 0;
        this.active = true;
        this.amount_gain = 1;
        this.name = NAME;
        this.loadRegion("unawakened");
        updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount_gain + DESCRIPTIONS[1];
    }

    public void onSpecificTrigger() {
        this.amount += amount_gain;
        updateDescription();
        flash();
        if (this.amount >= 10) {
            atb(new ChangeStanceAction(AwakenedPhase.STANCE_ID));
            updateDescription();
            this.amount = 0;
        }
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && card.type == AbstractCard.CardType.ATTACK)
            onSpecificTrigger();
    }

//    @Override
//    public int onLoseHp(int damageAmount) {
//        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && this.active && !AbstractDungeon.player.hasPower(BufferPower.POWER_ID)) {
//            onSpecificTrigger(damageAmount);
//        }
//        return super.onLoseHp(damageAmount);
//    }
}