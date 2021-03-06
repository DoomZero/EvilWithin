package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.MetronomePower;
import theTimeEater.powers.TimeWardPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Metronome extends AbstractTimeEaterCard {
    public final static String ID = makeID(Metronome.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Metronome() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MetronomePower());
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}