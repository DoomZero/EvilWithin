package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TimeEaterMod;
import theTimeEater.util.OnChangeTempoSubscriber;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class DejaVu extends AbstractTimeEaterCard implements OnChangeTempoSubscriber {
    public final static String ID = makeID(DejaVu.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public DejaVu() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.baseDamage = 4;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public void OnChangeTempo(TimeEaterMod.tempos tempo) {
        atb(new ExhaustToHandAction(this));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}