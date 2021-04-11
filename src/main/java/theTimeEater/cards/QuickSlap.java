package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.*;

public class QuickSlap extends AbstractTimeEaterCard {
    public final static String ID = makeID(QuickSlap.class.getSimpleName());
    // intellij stuff attack, enemy, common, 6, 3,  , , ,

    public QuickSlap() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        this.baseDamage = 8;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new DrawCardAction(this.magicNumber));
    }

    public void upp() {
        upgradeDamage(4);
//        upgradeMagicNumber(1);
    }
}