package theTimeEater.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.RecordPower;

import static theTimeEater.TimeEaterMod.makeID;

public class Playback extends AbstractTimeEaterCard {
    public final static String ID = makeID(Playback.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Playback() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.ALL);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        RecordPower pRecord = (RecordPower) p.getPower(RecordPower.POWER_ID);
        if (pRecord != null) pRecord.setHP();
        for (AbstractCreature mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            RecordPower mRecord = (RecordPower) mo.getPower(RecordPower.POWER_ID);
            if (mRecord != null) mRecord.setHP();
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}