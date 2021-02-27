package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class ExhaustDraw extends AbstractTimeEaterCard {
    public final static String ID = makeID(ExhaustDraw.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ExhaustDraw() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, this.magicNumber));
        //if upgraded, may choose zero
        atb(new ExhaustAction(this.magicNumber, upgraded));
    }

    public void upp() {
        uDesc();
    }
}