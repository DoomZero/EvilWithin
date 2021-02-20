package awakenedOne.cards;

import awakenedOne.powers.IronFeatherPower;
import awakenedOne.powers.SwiftFeatherPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static awakenedOne.AwakenedOneMod.makeID;
import static awakenedOne.util.Wiz.applyToEnemy;

public class SwiftFeather extends AbstractFeather{
    public final static String ID = makeID("SwiftFeather");
    private static final int MAGIC = 2;
    public final static String DESCRIPTION = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public SwiftFeather() {
        super(ID, CardRarity.UNCOMMON);
        baseMagicNumber = MAGIC;
        this.rawDescription = createFeatherDesc(DESCRIPTION);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        applyToEnemy(m, new SwiftFeatherPower(m, magicNumber, this));
    }
}
