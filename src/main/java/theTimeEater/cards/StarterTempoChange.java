package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.actions.FlipTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class StarterTempoChange extends AbstractTimeEaterCard {
    public final static String ID = makeID(StarterTempoChange.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public StarterTempoChange() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new FlipTempoAction());
        scry();
    }

    public void upp() {
        upgradeBlock(3);
    }
}