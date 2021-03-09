package theTimeEater.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theHexaghost.HexaMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class TempoSwitchPotion extends CustomPotion {
    public static final String POTION_ID = makeID(TempoSwitchPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public TempoSwitchPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FAIRY);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = HexaMod.placeholderColor;
    }


    public void initializeData() {
        this.potency = getPotency();
        this.description = (DESCRIPTIONS[0] + this.potency + DESCRIPTIONS[1]);
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(HexaMod.makeID("soulburn"))), BaseMod.getKeywordDescription(HexaMod.makeID("soulburn"))));
    }


    public void use(AbstractCreature target) {
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player, new BurnPower(target, this.potency), this.potency));
        atb(new EnterTempoAction());
    }


    public CustomPotion makeCopy() {
        return new TempoSwitchPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
