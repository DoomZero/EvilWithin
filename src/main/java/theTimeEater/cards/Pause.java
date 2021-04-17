package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;
import theTimeEater.powers.InvisiblePausePower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class Pause extends AbstractTimeEaterCard {
    public final static String ID = makeID(Pause.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Pause() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnterTempoAction(TimeEaterMod.tempos.PAUSE));
        applyToSelf(new InvisiblePausePower());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}