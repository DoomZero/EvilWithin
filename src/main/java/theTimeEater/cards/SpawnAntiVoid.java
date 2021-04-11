package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class SpawnAntiVoid extends AbstractTimeEaterCard {
    public final static String ID = makeID(SpawnAntiVoid.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public SpawnAntiVoid() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.cardsToPreview = new AntiVoid();
        this.baseMagicNumber = this.magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeTempCardInDrawPileAction(new AntiVoid(), this.magicNumber, true, true, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}