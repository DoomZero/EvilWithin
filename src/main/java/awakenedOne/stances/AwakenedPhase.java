package awakenedOne.stances;
import awakenedOne.AwakenedOneChar;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import guardian.GuardianMod;
import guardian.characters.GuardianCharacter;
import guardian.powers.DefensiveModeBooster;
import guardian.vfx.DefensiveModeStanceParticleEffect;

public class AwakenedPhase extends AbstractAwakenedStance{
    public static final String STANCE_ID = "awakened:AwakenedPhase";

    public AwakenedPhase() {
        this.ID = STANCE_ID;// 21
        this.name = GuardianCharacter.charStrings.TEXT[3];
        this.updateDescription();// 23
    }// 24

    @Override
    public String getKeywordString() {
        return "awakened:AwakenedPhase";
    }


    @Override
    public void onEnterStance() {
        super.onEnterStance();
    }

    //    @Override
//    public void updateAnimation() {
//    }

    @Override
    public void updateDescription() {

    }
}
