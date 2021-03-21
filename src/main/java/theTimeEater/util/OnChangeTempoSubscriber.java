package theTimeEater.util;

import theTimeEater.TheTimeEater;
import theTimeEater.TimeEaterMod;

public interface OnChangeTempoSubscriber {
    void OnChangeTempo(TimeEaterMod.tempos tempo); //not default because this class shouldn't be implemented without being used
}