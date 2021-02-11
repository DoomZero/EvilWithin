package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class EnergyDraw extends AbstractTimeEaterCard {
    public final static String ID = makeID(EnergyDraw.class.getSimpleName());
    //stupid intellij stuff skill, self, uncommon

    public EnergyDraw() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    private void fixDescription(){
        //extended desc does not have any [E] in it. magic starts at 1, and increased to 2 on first draw.
        String en = String.join("", Collections.nCopies(this.magicNumber, " [E]"));
        this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + en + cardStrings.EXTENDED_DESCRIPTION[1];
        this.initializeDescription();
    }

    public void triggerWhenDrawn() {
//        this.upgradeMagicNumber(1);
        this.baseMagicNumber++;
        this.magicNumber = this.baseMagicNumber;
        fixDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(this.magicNumber));
        this.baseMagicNumber = this.magicNumber = 1;
        fixDescription();
    }

    public void upp() {
        upgradeBlock(3);
    }
}