package awakenedOne.cards;

import awakenedOne.AwakenedOneMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import downfall.util.CardIgnore;
import guardian.GuardianMod;

import static awakenedOne.util.Wiz.*;

@CardIgnore
public abstract class AbstractFeather extends AbstractAwakenedCard {
    public static final String ID = AwakenedOneMod.makeID("Feather");
    public int DAMAGE = 1;
    public int MAGIC = 1;
    public String nonUniqueText = CardCrawlGame.languagePack.getCardStrings(ID).DESCRIPTION;

    public AbstractFeather(String cardID, CardRarity rarity) {
        super(cardID, 0, CardType.ATTACK, rarity, CardTarget.ENEMY);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.purgeOnUse = true;
    }

    String createFeatherDesc(String uniqueText){
        return nonUniqueText + " NL " + uniqueText;
    }

    @Override
    public void triggerWhenDrawn() {
        atb(new DrawCardAction(this.magicNumber));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    @Override
    public void upp() {

    }
}
