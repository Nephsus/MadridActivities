package kc.dcava.domain.interactors;

import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface PostAllSaveActivitiesInteractor {
    void execute(final LinkedList<MyActivity> myActivities,PostAllSaveActivitiesInteractorCompletion completion, PostAllSaveActivitiesInteractorFailure failure);
}
