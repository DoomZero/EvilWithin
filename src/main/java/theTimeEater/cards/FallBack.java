package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class FallBack extends AbstractTimeEaterCard {
    public final static String ID = makeID(FallBack.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FallBack() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new EnterTempoAction(TimeEaterMod.tempos.REWIND));
    }

    public void upp() {
        upgradeBlock(3);
    }
}