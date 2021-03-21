package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class SpringForward extends AbstractTimeEaterCard {
    public final static String ID = makeID(SpringForward.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 3,  , , ,

    public SpringForward() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        atb(new EnterTempoAction(TimeEaterMod.tempo.FORWARD));
    }

    public void upp() {
        upgradeDamage(3);
    }
}