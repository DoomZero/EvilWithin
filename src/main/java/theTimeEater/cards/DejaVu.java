package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.BetterDrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.actions.utility.DrawPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.ExhaustToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.DrawToHandAction;
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
    public void OnChangeTempo(TheTimeEater.tempos tempo){
//        att(new DiscardToHandAction(this));
//        att(new DrawToHandAction(this));

        atb(new ExhaustToHandAction(this));
//        atb(new ExhaustToHandAction(this));
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }
}