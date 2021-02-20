package awakenedOne.patches;

import awakenedOne.AwakenedOneMod;
import basemod.ReflectionHacks;
import charbosses.bosses.AbstractCharBoss;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;

import static com.megacrit.cardcrawl.powers.WeakPower.DESCRIPTIONS;

public class DisableStatusDecayPatch {
    public static String VulnDecayPowerID = AwakenedOneMod.makeID("BarbedFeatherPower");
    public static String WeakDecayPowerID = AwakenedOneMod.makeID("SappingFeatherPower");
    @SpirePatch(
            clz = VulnerablePower.class,
            method = "atEndOfRound"
    )
    public static class DisableVulnDecay {
        @SpirePrefixPatch
        public static SpireReturn Prefix(VulnerablePower instance) {
            boolean justApplied = (boolean) ReflectionHacks.getPrivate(instance, VulnerablePower.class, "justApplied");
            if (justApplied)
                ReflectionHacks.setPrivate(instance, VulnerablePower.class, "justApplied", false);
            if (instance.owner.hasPower(VulnDecayPowerID))
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = WeakPower.class,
            method = "atEndOfRound"
    )
    public static class DisableWeakDecay {
        @SpirePrefixPatch
        public static SpireReturn Prefix(WeakPower instance) {
            boolean justApplied = (boolean) ReflectionHacks.getPrivate(instance, WeakPower.class, "justApplied");
            if (justApplied)
                ReflectionHacks.setPrivate(instance, WeakPower.class, "justApplied", false);
            if (instance.owner.hasPower(WeakDecayPowerID))
                return SpireReturn.Return(null);
            else
                return SpireReturn.Continue();
        }
    }
}
