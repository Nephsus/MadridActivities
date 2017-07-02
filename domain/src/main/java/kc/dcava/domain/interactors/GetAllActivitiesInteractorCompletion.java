package kc.dcava.domain.interactors;

import android.support.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

import kc.dcava.domain.managers.network.entities.ActivityEntity;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public interface GetAllActivitiesInteractorCompletion {
    void completion(@NonNull final LinkedList<MyActivity> activityEntities);

}
