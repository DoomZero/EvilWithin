package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockExtendablePower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.adp;
import static theTimeEater.util.Wiz.atb;

public class LeapPastFate extends AbstractTimeEaterCard {
    public final static String ID = makeID(LeapPastFate.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LeapPastFate() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 0;
        this.retain = true;
    }

    private void calcDam(){
        TimeLockExtendablePower p = (TimeLockExtendablePower) adp().getPower(TimeLockExtendablePower.POWER_ID);
        baseDamage = 0;
        if (p == null) return;

        if (p.amount > 0){
            baseDamage = p.amount;
        }
        p.remove();
    }

    private void updateDesc(){
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void calculateCardDamage(AbstractMonster m) {
        calcDam();
        super.calculateCardDamage(m);
        updateDesc();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        calculateCardDamage(m);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void applyPowers() {
        calcDam();
        super.applyPowers();
        updateDesc();
    }

    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    public void upp() {
        upgradeDamage(10);
    }
}