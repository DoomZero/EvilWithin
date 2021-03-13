package awakenedOne.cards;

import awakenedOne.powers.BarbedFeatherPower;
import awakenedOne.powers.SappingFeatherPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.*;

public class SappingFeather extends AbstractFeather{
    public final static String ID = makeID(SappingFeather.class.getSimpleName());
    private static final int MAGIC = 1;
    public final static String DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public SappingFeather() {
        super(ID, CardRarity.COMMON);
        baseMagicNumber = MAGIC;
        this.rawDescription = createFeatherDesc(DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        applyToEnemy(m, autoWeak(m, magicNumber));
        applyFeatherPower(m, new SappingFeatherPower(m, magicNumber, this));
    }
}
