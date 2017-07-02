package kc.dcava.domain.interactors;

/**
 * Created by davidcavajimenez on 25/6/17.
 */

public interface GetTimeLastUpdateInteractor {

    void execute(String filename, String key, Runnable activitiesAreCached, Runnable activitiesAreNotCached);

}
