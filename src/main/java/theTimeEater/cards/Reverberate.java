package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;

public class Reverberate extends AbstractTimeEaterCard {
    public final static String ID = makeID(Reverberate.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public Reverberate() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseDamage = 4;
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            allDmg(AbstractGameAction.AttackEffect.LIGHTNING);
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}