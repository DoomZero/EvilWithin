package theTimeEater.powers;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.util.Wiz.*;

public class InstantReplayPower extends AbstractTimeEaterPower implements NonStackablePower {
    public static final String POWER_ID = TimeEaterMod.makeID(InstantReplayPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public InstantReplayPower(){
        super(NAME, POWER_ID, PowerType.BUFF, AbstractDungeon.player);
        loadRegion("time");
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean unused) {
        atb(new EnterTempoAction(TimeEaterMod.tempos.FORWARD));
        this.remove();
    }

    @Override
    public AbstractPower makeCopy() {
        return new InstantReplayPower();
    }
}
