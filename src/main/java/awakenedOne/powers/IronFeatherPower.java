//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package awakenedOne.powers;

import awakenedOne.AwakenedOneMod;
import awakenedOne.cards.AbstractFeather;
import awakenedOne.cards.IronFeather;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public class IronFeatherPower extends AbstractFeatherPower {
    public static String POWER_ID = AwakenedOneMod.makeID("IronFeatherPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public IronFeatherPower(AbstractCreature owner, int amount, AbstractFeather featherCard) {
        super(owner, featherCard);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("ritual");
    }

//    public void stackPower(int stackAmount) {
//        this.fontScale = 8.0F;
//        this.amount += stackAmount;
//        this.updateDescription();
//    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            this.addToTop(new GainBlockAction(AbstractDungeon.player, this.amount, Settings.FAST_MODE));
        }
        return damageAmount;
    }

    public void updateDescription() {
        this.description = createFeatherPowerDesc(DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1], NAME);
    }
}
