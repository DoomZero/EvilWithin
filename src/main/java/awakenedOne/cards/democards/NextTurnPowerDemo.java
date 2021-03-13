package awakenedOne.cards.democards;

import awakenedOne.cards.AbstractAwakenedCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.applyToSelfNextTurn;

public class NextTurnPowerDemo extends AbstractAwakenedCard {

    public final static String ID = makeID(NextTurnPowerDemo.class.getSimpleName());
    // intellij stuff skill, self, uncommon

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public NextTurnPowerDemo() {
        super(ID, 2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelfNextTurn(new ThornsPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
} 