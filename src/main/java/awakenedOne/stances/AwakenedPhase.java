package awakenedOne.stances;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import guardian.characters.GuardianCharacter;
import guardian.vfx.DefensiveModeStanceParticleEffect;

import static awakenedOne.AwakenedOneMod.AWAKENED;

public class AwakenedPhase extends AbstractStance {
    public static final String STANCE_ID = "awakened:AwakenedPhase";

    public AwakenedPhase() {
        this.ID = STANCE_ID;
        this.name = "Awakened Phase";
        this.updateDescription();
    }

//    @Override
//    public String getKeywordString() {
//        return "awakened:AwakenedPhase";
//    }


    @Override
    public void onEnterStance() {
        super.onEnterStance();
    }

    @Override
    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {

            this.particleTimer -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer < 0.0F) {
                this.particleTimer = 0.04F;
                AbstractDungeon.effectsQueue.add(new DefensiveModeStanceParticleEffect(new Color(1.0F, 0.9F, 0.7F, 0.0F)));
            }
        }


        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("DefensiveMode"));
        }
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 10));
        if (card.hasTag(AWAKENED)) {
            action.exhaustCard = true;
        }
    }

    @Override
    public void updateDescription() {

    }
}
