package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class ReverseGrip extends AbstractTimeEaterCard {
    public final static String ID = makeID(ReverseGrip.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ReverseGrip()  {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
    }

    public void use(AbstractPlayer pp, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        TheTimeEater p = (TheTimeEater) pp;
        if (p.tempo == TheTimeEater.tempos.REWIND)  atb(new EnterTempoAction(TheTimeEater.tempos.FORWARD));
        if (p.tempo == TheTimeEater.tempos.FORWARD) atb(new EnterTempoAction(TheTimeEater.tempos.REWIND));
    }

    public void upp() {
        upgradeDamage(3);
    }
}