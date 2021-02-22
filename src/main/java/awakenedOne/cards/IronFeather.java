package awakenedOne.cards;

import awakenedOne.powers.IronFeatherPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.applyToEnemy;

public class IronFeather extends AbstractFeather{
    public final static String ID = makeID("IronFeather");
    private static final int MAGIC = 1;
    public final static String DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public IronFeather() {
        super(ID, CardRarity.COMMON);
        baseMagicNumber = MAGIC;
        this.rawDescription = createFeatherDesc(DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        applyFeatherPower(m, new IronFeatherPower(m, magicNumber, this));
    }
}
