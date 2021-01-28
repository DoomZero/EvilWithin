package theTimeEater.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import downfall.util.CardIgnore;

import static theTimeEater.TimeEaterMod.makeID;

@CardIgnore
public class TimeEaterModalChoiceCard extends AbstractTimeEaterCard {

    //RIP Octopus.
    //Why isn't this abstract? Because AbstractCard MakeCopy for Lambdas would be very difficult. Maybe some other time. Also Java hates instantiating abstracts unless they're SAMs.
    //Typically you'll want to add the EasyModalChoiceAction to the bottom, and have the TimeEaterModalChoiceCard Runnables inside add to top.

    private Runnable onUseOrChosen;
    private String passedName;
    private String passedDesc;

    public TimeEaterModalChoiceCard(String name, String description, Runnable onUseOrChosen) {
        super(makeID(name), -2, CardType.SKILL, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        this.name = this.originalName = passedName = name;
        this.rawDescription = passedDesc = description;
        this.onUseOrChosen = onUseOrChosen;
        initializeTitle();
        initializeDescription();
    }

    @Override
    public void onChoseThisOption() {
        onUseOrChosen.run();
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        onUseOrChosen.run();
    }

    @Override
    public void upp() {

    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new TimeEaterModalChoiceCard(passedName, passedDesc, onUseOrChosen);
    }
}
