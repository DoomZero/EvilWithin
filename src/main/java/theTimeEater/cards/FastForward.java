package theTimeEater.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class FastForward extends AbstractTimeEaterCard {
    public final static String ID = makeID(FastForward.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FastForward()  {
        super(ID, 3, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnterTempoAction(TheTimeEater.tempos.FORWARD));
        atb(new DiscardAction(p,p,p.hand.size(),true));
        atb(new DrawCardAction(p,5));
        atb(new RefundAction(this));
    }

    public void upp() {
        this.exhaust = false;
        uDesc();
    }
}