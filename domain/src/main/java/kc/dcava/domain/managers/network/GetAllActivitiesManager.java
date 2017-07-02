package kc.dcava.domain.managers.network;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public interface GetAllActivitiesManager<C,F> {

    void getAllActivities(C completion, F failure);
}
