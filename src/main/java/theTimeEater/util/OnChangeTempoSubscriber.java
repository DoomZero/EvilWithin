package theTimeEater.util;

import theTimeEater.TheTimeEater;

public interface OnChangeTempoSubscriber {
    void OnChangeTempo(TheTimeEater.tempos tempo); //not default because this class shouldn't be implemented without being used
}