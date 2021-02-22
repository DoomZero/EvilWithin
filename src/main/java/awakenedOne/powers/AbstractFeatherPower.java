//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package awakenedOne.powers;

import awakenedOne.AwakenedOneMod;
import awakenedOne.cards.AbstractFeather;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import static awakenedOne.util.Wiz.*;


public abstract class AbstractFeatherPower extends TwoAmountPower implements NonStackablePower {
    public static String POWER_ID = AwakenedOneMod.makeID("FeatherPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public AbstractFeather featherCard;

    public AbstractFeatherPower(AbstractCreature owner, AbstractFeather featherCard) {
        this.owner = owner;
        this.featherCard = featherCard;
        this.amount2 = 2;
        this.updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()
                && amount2 == 1 && !isPlayer) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            makeInDiscard(featherCard);
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

    public void onDeath() {
        makeInDiscard(featherCard);
    }

    String createFeatherPowerDesc(String uniqueText, String cardName){
        if (amount2 == 1)
            return uniqueText + " NL " + DESCRIPTIONS[2] + cardName + DESCRIPTIONS[3];
        else
            return uniqueText + " NL " + DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + FontHelper.colorString(cardName, "y") + DESCRIPTIONS[3];
    }
}
