package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;
import theTimeEater.powers.InstantReplayPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class InstantReplay extends AbstractTimeEaterCard {
    public final static String ID = makeID(InstantReplay.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public InstantReplay() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnterTempoAction(TimeEaterMod.tempos.REWIND));
        atb(new ApplyPowerAction(p, p, new InstantReplayPower()));
    }

    public void upp() {
        this.exhaust = false;
        uDesc();
    }
}