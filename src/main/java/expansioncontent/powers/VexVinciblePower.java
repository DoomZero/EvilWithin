package expansioncontent.powers;

import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import static expansioncontent.expansionContentMod.makeID;

public class VexVinciblePower extends TwoAmountPower {
    public static final String POWER_ID = makeID("VexVinciblePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public int maxAmt;

    public VexVinciblePower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.amount2 = 10;
        this.maxAmt = amount;
        this.updateDescription();
        this.loadRegion("heartDef");
        this.priority = 99;// 23
    }

    public int onLoseHp(int damageAmount) {
        boolean activated = false;
        if (damageAmount > this.amount2) {
            damageAmount = this.amount2;
            activated = true;
        }

        this.amount2 -= damageAmount;
        if (this.amount2 < 0) {
            this.amount2 = 0;
        }

        this.updateDescription();
        if (activated) {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
        return damageAmount;
    }

    public void updateDescription() {
        if (this.amount2 == 1) {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + amount2 + DESCRIPTIONS[4];
        }
    }
}
