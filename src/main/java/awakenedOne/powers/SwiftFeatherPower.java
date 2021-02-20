//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package awakenedOne.powers;

import awakenedOne.AwakenedOneMod;
import awakenedOne.cards.AbstractFeather;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static awakenedOne.util.Wiz.atb;


public class SwiftFeatherPower extends AbstractFeatherPower {
    public static String POWER_ID = AwakenedOneMod.makeID("SwiftFeatherPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private boolean attackedThisTurn;

    public SwiftFeatherPower(AbstractCreature owner, int amount, AbstractFeather featherCard) {
        super(owner, featherCard);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = 2;
        this.updateDescription();
        this.loadRegion("ritual");
        this.attackedThisTurn = false;
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS &&
                info.owner != null && info.owner != this.owner && !attackedThisTurn) {
            this.flash();
            atb(new DrawCardAction(AbstractDungeon.player, this.amount));
            attackedThisTurn = true;
        }
        return damageAmount;
    }

    public void atStartOfTurn() {
        attackedThisTurn = false;
    }

    public void updateDescription() {
        this.description = createFeatherPowerDesc(DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1], NAME);
    }
}
