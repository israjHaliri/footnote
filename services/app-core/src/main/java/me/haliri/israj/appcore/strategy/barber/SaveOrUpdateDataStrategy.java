package me.haliri.israj.appcore.strategy.barber;

import java.util.List;

/**
 * Created by israjhaliri on 12/6/17.
 */
public interface SaveOrUpdateDataStrategy<T> {

    void process(T parameters);

}
