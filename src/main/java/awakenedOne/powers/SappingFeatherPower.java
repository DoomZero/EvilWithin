//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package awakenedOne.powers;

import awakenedOne.AwakenedOneMod;
import awakenedOne.cards.AbstractFeather;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;


public class SappingFeatherPower extends AbstractFeatherPower {
    public static String POWER_ID = AwakenedOneMod.makeID("SappingFeatherPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SappingFeatherPower(AbstractCreature owner, int amount, AbstractFeather featherCard) {
        super(owner, featherCard);
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("ritual");
    }

    public void updateDescription() {
        this.description = createFeatherPowerDesc(DESCRIPTIONS[0], NAME);
    }
}
