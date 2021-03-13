package theTimeEater.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import theTimeEater.TimeEaterMod;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class TimeLockSelfPotion extends CustomPotion {
    public static final String POTION_ID = makeID(TimeLockSelfPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public TimeLockSelfPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.FAIRY);
        this.isThrown = false;
        this.targetRequired = false;
        this.labOutlineColor = TimeEaterMod.characterColor;
    }


    public void initializeData() {
        this.potency = getPotency();
        this.description = DESCRIPTIONS[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
//        this.tips.add(new PowerTip(TipHelper.capitalize(BaseMod.getKeywordTitle(HexaMod.makeID("soulburn"))), BaseMod.getKeywordDescription(HexaMod.makeID("soulburn"))));
    }

    @Override
    public void use(AbstractCreature target) {
        applyToSelf(new TimeLockPower(adp(), 1));
    }

    @Override
    public CustomPotion makeCopy() {
        return new TimeLockSelfPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
