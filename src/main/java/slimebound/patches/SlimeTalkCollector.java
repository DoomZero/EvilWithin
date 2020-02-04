package slimebound.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.EventStrings;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import slimebound.SlimeboundMod;
import slimebound.characters.SlimeboundCharacter;
import expansioncontent.relics.StudyCardRelic;

@SpirePatch(clz = TheCollector.class, method = "takeTurn")
public class SlimeTalkCollector {
    public static final String[] DESCRIPTIONS;
    private static final EventStrings eventStrings;

    static {
        eventStrings = CardCrawlGame.languagePack.getEventString("Slimebound:SlimeTalk");
        DESCRIPTIONS = eventStrings.DESCRIPTIONS;
    }

    public static void Prefix(TheCollector sb) {
        if (AbstractDungeon.player instanceof SlimeboundCharacter && SlimeboundMod.slimeTalkedCollector == false && AbstractDungeon.player.hasRelic(StudyCardRelic.ID)) {
            AbstractDungeon.actionManager.addToBottom(new TalkAction(sb, DESCRIPTIONS[4], 1.0F, 2.0F));
            SlimeboundMod.slimeTalkedCollector = true;
        }
    }
}