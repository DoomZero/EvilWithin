package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DoubleDamagePower;
import theTimeEater.powers.IntangibleNextTurnPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class Redshift extends AbstractTimeEaterCard {
    public final static String ID = makeID(Redshift.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public Redshift() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster unused) {
        applyToSelf(new DoubleDamagePower(p, 1, false));
        forAllMonstersLiving(m -> applyToEnemy(m, new IntangibleNextTurnPower(m, 1)));
    }

    public void upp() {
        upgradeBlock(3);
    }
}