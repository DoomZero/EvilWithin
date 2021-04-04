package theTimeEater.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import downfall.util.LocalizeHelper;
import theHexaghost.cards.seals.ThirdSeal;
import theTimeEater.TimeEaterMod;
import theTimeEater.util.OnChangeTempoSubscriber;

import static theTimeEater.util.Wiz.adp;
import static theTimeEater.util.Wiz.atb;

public class HastePower extends AbstractTimeEaterPower implements CloneablePowerInterface {
    public static final String POWER_ID = TimeEaterMod.makeID(HastePower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public HastePower(){
        this(1,1);
    }

    public HastePower(int rate, int start) {
        super(NAME, POWER_ID, PowerType.BUFF, adp(), rate, start,false);

        this.amount = rate;
        this.amount2 = start;
        loadRegion("time");
    }

    @Override
    public void atEndOfTurn(boolean unused){
        atb(new DamageAllEnemiesAction(adp(), this.amount2, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));

        HastePower thisPow = this;
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                thisPow.amount2 += thisPow.amount;
                thisPow.updateDescription();
            }
        });
    }

    @Override
    public void stackPower(int rate) {
        this.fontScale = 8.0F;
        this.amount += rate;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1] + this.amount + LocalizedStrings.PERIOD;
    }

    @Override
    public AbstractPower makeCopy() {
        return new HastePower(this.amount, this.amount2);
    }
}
