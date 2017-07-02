package kc.dcava.domain.managers.db;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface GetAllSaveActivitiesManager {

     void getAllSaveActivitiesManagerImpl(final String query, final GetAllSaveActivitiesManagerCompletion completion, final GetAllSaveActivitiesManagerFailure failure );
}
