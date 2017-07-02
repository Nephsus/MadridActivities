package kc.dcava.domain.managers.network;

import android.support.annotation.NonNull;
import java.util.List;
import kc.dcava.domain.managers.network.entities.ActivityEntity;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public interface GetRefreshImagesManagerCompletion {

    void completion(@NonNull final List<ActivityEntity> activityEntities);

}
