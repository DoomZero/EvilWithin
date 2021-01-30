package awakenedOne.cards.democards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import awakenedOne.actions.EasyXCostAction;
import awakenedOne.cards.AbstractAwakenedCard;

import static awakenedOne.util.Wiz.*;
import static awakenedOne.AwakenedOneMod.makeID;

public class AwakenedXCostDemo extends AbstractAwakenedCard {
    public final static String ID = makeID("AwakenedXCostDemo");
    // intellij stuff attack, enemy, rare, , , , , 0, 1

    public AwakenedXCostDemo() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 0;
        baseDamage = 5;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            for (int i = 0; i < effect + params[0]; i++) {
                dmgTop(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
            }
            applyToSelfTop(new StrengthPower(p, effect + params[0]));
            return true;
        }, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}