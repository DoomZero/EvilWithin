package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theTimeEater.TimeEaterMod;

import java.util.ArrayList;

import static theTimeEater.util.Wiz.*;

public class DrawVoidPower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(DrawVoidPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    /*public DrawVoidPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.isTurnBased = false;
        this.type = PowerType.DEBUFF;
        this.updateDescription();
        loadRegion("time");
    }*/

    public DrawVoidPower(AbstractCreature owner, int amount){
        super(NAME, POWER_ID, PowerType.DEBUFF, false, owner, amount);
        loadRegion("time");
    }

    @Override
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    @Override
    public void onCardDraw(AbstractCard notUsed){
        if (this.amount <= 0){
            atb(new RemoveSpecificPowerAction(this.owner, this.owner,this));
        }
    }

    @Override
    public void atEndOfTurn(boolean notUsed){
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner,this));
    }

    @Override
    public void updateDescription() {
        if (this.amount <= 1){
            description = DESCRIPTIONS[0];
        }
        else {
            description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DrawVoidPower(owner, this.amount);
    }
}
