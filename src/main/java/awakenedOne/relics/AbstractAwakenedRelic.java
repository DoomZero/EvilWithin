package awakenedOne.relics;

import awakenedOne.AwakenedOneMod;
import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import downfall.util.TextureLoader;


public abstract class AbstractAwakenedRelic extends CustomRelic {
    public AbstractAwakenedRelic(String ID, RelicTier tier, LandingSound sound) {
        super(ID, TextureLoader.getTexture(AwakenedOneMod.makeRelicPath(ID.replace(AwakenedOneMod.getModID() + ":", "") + ".png")), TextureLoader.getTexture(AwakenedOneMod.makeRelicOutlinePath(ID.replace(AwakenedOneMod.getModID() + ":", "") + ".png")), tier, sound);
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    private static void atb(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public static void applyToSelf(AbstractPower po) {
        atb(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, po, po.amount));
    }
}