package kc.dcava.domain.interactors;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface GetAllSaveActivitiesInteractor {
     void execute(final String query, final GetAllSaveActivitiesInteractorCompletion completion, final GetAllSaveActivitiesInteractorFailure failure);
}
