package theTimeEater.powers;


import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import downfall.util.TextureLoader;
import expansioncontent.expansionContentMod;
import theTimeEater.TimeEaterMod;

import static theTimeEater.util.Wiz.*;

public class TimeEaterWarpPower extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(TimeEaterWarpPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(expansionContentMod.getModID() + "Resources/images/powers/ChronoBoost84.png");
    private static final Texture tex32 = TextureLoader.getTexture(expansionContentMod.getModID() + "Resources/images/powers/ChronoBoost32.png");


    public TimeEaterWarpPower(int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = adp();
        this.amount = amount;
        this.type = PowerType.BUFF;
//        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        this.amount2++;
        if (this.amount2 >= 12) {
            this.amount2 = 0;
            applyToSelf(new StrengthPower(adp(), this.amount));
        }
        this.updateDescription();
    }

    @Override
    public AbstractPower makeCopy() {
        return new TimeEaterWarpPower(this.amount);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + (12 - (amount2)) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}




