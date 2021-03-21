package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class ReliveThePast extends AbstractTimeEaterCard {
    public final static String ID = makeID(ReliveThePast.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public ReliveThePast()  {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (TimeEaterMod.tempo == TimeEaterMod.tempos.REWIND){
            atb(new DrawCardAction(p, 2));
        } else {
            atb(new EnterTempoAction(TimeEaterMod.tempos.REWIND));
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}