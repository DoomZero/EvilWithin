package theTimeEater.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theTimeEater.powers.TimeLockPower;

import static theTimeEater.TimeEaterMod.makeID;
import static theTimeEater.util.Wiz.adp;

public class LeapPastFate extends AbstractTimeEaterCard {
    public final static String ID = makeID(LeapPastFate.class.getSimpleName());
    // intellij stuff attack, enemy, basic, 6, 3,  , , ,

    public LeapPastFate() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 0;
//        this.retain = true;
//        this.exhaust = true;
    }

    private void calcDam(){
        TimeLockPower p = (TimeLockPower) adp().getPower(TimeLockPower.POWER_ID);
        baseDamage = 0;
        if (p == null) return;

        if (p.amount > 0){
            baseDamage = p.amount;
        }
    }

    private void updateDesc(){
        this.rawDescription = cardStrings.DESCRIPTION;
        this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0];
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

        TimeLockPower tl = (TimeLockPower) adp().getPower(TimeLockPower.POWER_ID);
        if (tl != null){
            tl.remove();
        }
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