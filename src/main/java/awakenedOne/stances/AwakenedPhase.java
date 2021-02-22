package awakenedOne.stances;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import guardian.characters.GuardianCharacter;

import static awakenedOne.AwakenedOneMod.AWAKENED;

public class AwakenedPhase extends AbstractAwakenedStance{
    public static final String STANCE_ID = "awakened:AwakenedPhase";

    public AwakenedPhase() {
        this.ID = STANCE_ID;// 21
        this.name = GuardianCharacter.charStrings.TEXT[3];
        this.updateDescription();// 23
    }// 24

    @Override
    public String getKeywordString() {
        return "awakened:AwakenedPhase";
    }


    @Override
    public void onEnterStance() {
        super.onEnterStance();
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AWAKENED)) {
            action.exhaustCard = true;
        }
    }

    @Override
    public void updateDescription() {

    }
}
