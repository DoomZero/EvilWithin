package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.RecordPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.atb;

public class Record extends AbstractTimeEaterCard {
    public final static String ID = makeID(Record.class.getSimpleName());
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public Record() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL);
        this.exhaust = true;
        this.cardsToPreview = new Playback();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RecordPower(p));
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            applyToEnemy(mo, new RecordPower(mo));
        }
        atb(new MakeTempCardInDrawPileAction(new Playback(), 1, true, true, false));
    }

    public void upp() {
        upgradeBaseCost(0);
        this.cardsToPreview.upgrade();
    }
}