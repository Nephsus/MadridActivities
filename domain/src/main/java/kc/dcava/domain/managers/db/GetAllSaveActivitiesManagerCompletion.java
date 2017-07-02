package kc.dcava.domain.managers.db;

import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public interface GetAllSaveActivitiesManagerCompletion {

    void completion(LinkedList<MyActivity> myActivities);
}
