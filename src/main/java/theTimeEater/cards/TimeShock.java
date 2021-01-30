package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;

public class TimeShock extends AbstractTimeEaterCard {
    public final static String ID = makeID(TimeShock.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public TimeShock() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (m.hasPower(TimeLockPower.POWER_ID)) {
                    AbstractPower tl = m.getPower(TimeLockPower.POWER_ID);
                    applyToEnemyTop(m, new TimeLockPower(m, tl.amount));
                }
            }
        });
    }

    public void upp() {
        upgradeBlock(3);
    }
}