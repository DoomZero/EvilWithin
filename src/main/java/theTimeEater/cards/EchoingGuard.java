package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class EchoingGuard extends AbstractTimeEaterCard {
    public final static String ID = makeID(EchoingGuard.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public EchoingGuard() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        if (p.hasPower(TimeLockPower.POWER_ID)){
            blck();
        }
    }

    public void upp() {
        upgradeBlock(3);
    }
}