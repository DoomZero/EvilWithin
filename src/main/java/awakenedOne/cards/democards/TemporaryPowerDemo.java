package awakenedOne.cards.democards;

import awakenedOne.cards.AbstractAwakenedCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.NirvanaPower;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.applyToSelfTemp;

public class TemporaryPowerDemo extends AbstractAwakenedCard {

    public final static String ID = makeID("TemporaryPowerDemo");
    // intellij stuff power, self, uncommon

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 2;

    public TemporaryPowerDemo() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelfTemp(new NirvanaPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }
} 