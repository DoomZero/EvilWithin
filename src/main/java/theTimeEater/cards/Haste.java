package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.HastePower;

import static theTimeEater.TimeEaterMod.makeID;

public class Haste extends AbstractTimeEaterCard {
    public final static String ID = makeID(Haste.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Haste() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HastePower(1, 1));
    }

    public void upp() {
        this.isInnate = true;
        uDesc();
    }
}