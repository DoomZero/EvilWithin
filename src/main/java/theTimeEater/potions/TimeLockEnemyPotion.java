package theTimeEater.potions;

import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class TimeLockEnemyPotion extends CustomPotion {
    public static final String POTION_ID = makeID(TimeLockEnemyPotion.class.getSimpleName());
    private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
    public static final String NAME = potionStrings.NAME;
    public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS;


    public TimeLockEnemyPotion() {
        super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.SPHERE, PotionColor.BLUE);
        this.isThrown = true;
        this.targetRequired = true;
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
        applyToEnemy((AbstractMonster) target, new TimeLockPower(target, 1));
    }

    @Override
    public CustomPotion makeCopy() {
        return new TimeLockEnemyPotion();
    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }
}
