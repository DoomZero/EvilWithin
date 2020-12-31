package downfall.patches;

import automaton.FunctionHelper;
import automaton.cards.RecursiveStrike;
import charbosses.bosses.AbstractCharBoss;
import charbosses.bosses.Silent.CharBossSilent;
import charbosses.bosses.Silent.NewAge.ArchetypeAct2MirrorImageNewAge;
import charbosses.monsters.MirrorImageSilent;
import charbosses.powers.FakeOrRealPower;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.SpawnMonsterAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CtBehavior;

@SpirePatch(
        clz = GameActionManager.class,
        method = "getNextAction"
)
public class GlobalTurnStartCheckPatch {
    @SpireInsertPatch(
            locator = Locator.class
    )
    public static void Insert(GameActionManager __instance) {
        if (FunctionHelper.held != null) {
            for (AbstractCard q : FunctionHelper.held.group) {
                if (q instanceof RecursiveStrike) {
                    q.atTurnStart();
                }
            }
        }
        if (AbstractCharBoss.boss != null) {
            if (AbstractCharBoss.boss.chosenArchetype instanceof ArchetypeAct2MirrorImageNewAge) {
                ((CharBossSilent)AbstractCharBoss.boss).spawnImage(false);
            }
        }
    }

    private static class Locator extends SpireInsertLocator {
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractPlayer.class, "applyStartOfTurnRelics");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}