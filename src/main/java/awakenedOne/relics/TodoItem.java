package awakenedOne.relics;

import awakenedOne.AwakenedOneChar;
import awakenedOne.AwakenedOneMod;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static awakenedOne.AwakenedOneMod.makeID;

public class TodoItem extends AbstractAwakenedRelic {
    public static final String ID = AwakenedOneMod.makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT);
    }
}
