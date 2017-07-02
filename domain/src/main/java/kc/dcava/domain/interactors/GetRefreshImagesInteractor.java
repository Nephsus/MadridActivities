package kc.dcava.domain.interactors;

import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public interface GetRefreshImagesInteractor {

     void execute(LinkedList<MyActivity> activityList, final Runnable completion);
}
