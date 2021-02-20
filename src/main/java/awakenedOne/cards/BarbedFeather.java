package awakenedOne.cards;

import awakenedOne.powers.BarbedFeatherPower;
import awakenedOne.powers.IronFeatherPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.*;

public class BarbedFeather extends AbstractFeather{
    public final static String ID = makeID("BarbedFeather");
    private static final int MAGIC = 1;
    public final static String DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public BarbedFeather() {
        super(ID, CardRarity.COMMON);
        baseMagicNumber = MAGIC;
        this.rawDescription = createFeatherDesc(DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, new BarbedFeatherPower(m, magicNumber, this));
    }
}
