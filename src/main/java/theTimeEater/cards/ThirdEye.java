package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theTimeEater.TimeEaterMod.makeID;

public class ThirdEye extends AbstractTimeEaterCard {
    public final static String ID = makeID(ThirdEye.class.getSimpleName());
    // intellij stuff skill, self, basic, 5, 3,  , , ,

    public ThirdEye() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 3;   //scry num
        this.block = this.baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        scry();
    }

    public void upp() {
        upgradeBlock(3);
    }
}