package kc.dcava.domain.interactors;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public interface GetAllActivitiesInteractor {


    void execute(GetAllActivitiesInteractorCompletion completion, GetAllActivitiesInteractorFailure failure);
}
