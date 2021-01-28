package theTimeEater.relics;

import theTimeEater.TheTimeEater;

import static theTimeEater.TimeEaterMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheTimeEater.Enums.TIME_EATER_COLOR);
    }
}
