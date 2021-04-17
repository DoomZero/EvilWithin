package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class HeadSlam extends AbstractTimeEaterCard {
    public final static String ID = makeID(HeadSlam.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public HeadSlam() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 22;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DrawCardAction(p, baseMagicNumber));
    }

    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(1);
    }
}