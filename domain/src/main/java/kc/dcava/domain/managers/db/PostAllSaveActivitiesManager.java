package kc.dcava.domain.managers.db;

import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface PostAllSaveActivitiesManager {

    void postAllSaveActivities(final LinkedList<MyActivity> myActivities, final PostAllSaveActivitiesManagerCompletion completion, final PostAllSaveActivitiesManagerFailure failure);
}
