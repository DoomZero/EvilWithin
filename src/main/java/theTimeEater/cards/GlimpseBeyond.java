package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.TheTimeEater;
import theTimeEater.actions.EnterTempoAction;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class GlimpseBeyond extends AbstractTimeEaterCard {
    public final static String ID = makeID(GlimpseBeyond.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public GlimpseBeyond()  {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (((TheTimeEater) p).tempo == TheTimeEater.tempos.FORWARD){
            atb(new DrawCardAction(p, 2));
        } else {
            atb(new EnterTempoAction(TheTimeEater.tempos.FORWARD));
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}