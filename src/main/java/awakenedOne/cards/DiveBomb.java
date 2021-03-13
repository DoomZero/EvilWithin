package awakenedOne.cards;

import awakenedOne.stances.AwakenedPhase;
import awakenedOne.util.OnAwakenSubscriber;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.stances.AbstractStance;

import static awakenedOne.AwakenedOneMod.*;
import static awakenedOne.util.Wiz.*;

public class DiveBomb extends AbstractAwakenedCard implements OnAwakenSubscriber {
    public final static String ID = makeID(DiveBomb.class.getSimpleName());
    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 4;

    public DiveBomb() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(AWAKENED);
        isMultiDamage = false;
    }

    @Override
    public void onAwaken(){
        this.target = CardTarget.ALL_ENEMY;
        this.isMultiDamage = true;
        this.exhaust = true;
        initializeDescription();
    }

//    @Override
//    public void switchedStance(){
//        AbstractStance currentStance = adp().stance;
//        if (currentStance != null && currentStance.ID == AwakenedPhase.STANCE_ID){
//            this.target = CardTarget.ALL_ENEMY;
//            this.isMultiDamage = true;
//            this.exhaust = true;
//            initializeDescription();
//        }
//    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isAwakened)
            allDmg(AbstractGameAction.AttackEffect.NONE);
        else
            dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}