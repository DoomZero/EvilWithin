package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class RetraceYourSteps extends AbstractTimeEaterCard {
    public final static String ID = makeID(RetraceYourSteps.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public RetraceYourSteps() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new EnterTempoAction(TheTimeEater.tempos.REWIND));
    }

    public void upp() {
        upgradeBlock(3);
    }
}