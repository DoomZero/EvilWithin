package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import slimebound.actions.TriggerStartOfTurnEffectsAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class ManipulateTime extends AbstractTimeEaterCard {
    public final static String ID = makeID(ManipulateTime.class.getSimpleName());

    private static final int BLOCK = 10;
    private static final int UPGRADE_BLOCK = 4;

    public ManipulateTime() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);

        baseBlock = BLOCK;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("POWER_TIME_WARP", 0.05F);
        AbstractDungeon.effectsQueue.add(new com.megacrit.cardcrawl.vfx.BorderFlashEffect(com.badlogic.gdx.graphics.Color.GOLD, true));
        AbstractDungeon.topLevelEffectsQueue.add(new com.megacrit.cardcrawl.vfx.combat.TimeWarpTurnEndEffect());

        blck();
        atb(new TriggerStartOfTurnEffectsAction(p));
    }

    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        this.exhaust = false;
    }

}

