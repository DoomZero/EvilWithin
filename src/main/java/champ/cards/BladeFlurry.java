package champ.cards;

import champ.ChampMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BladeFlurry extends AbstractChampCard {

    public final static String ID = makeID("BladeFlurry");

    //stupid intellij stuff attack, enemy, common

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;

    public BladeFlurry() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
        tags.add(ChampMod.FINISHER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //finisher();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int x = 0;
                for (AbstractCard q : p.hand.group) if (q.hasTag(ChampMod.TECHNIQUE)) x++;
                for (int i = 0; i < x; i++) att(new DamageAction(m, makeInfo(), AttackEffect.SLASH_DIAGONAL));
            }
        });
        if (upgraded) {
            if (gcombo()) exhaust = false;
        }
        finisher();
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = gcombo() ? GOLD_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}