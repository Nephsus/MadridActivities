package kc.dcava.domain.interactors;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import kc.dcava.domain.managers.network.GetAllActivitiesManagerCompletion;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerFailure;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerImpl;
import kc.dcava.domain.managers.network.entities.ActivityEntity;
import kc.dcava.domain.managers.network.parsers.MyActivitiesMapperFromEntities;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public class GetAllActivitiesInteractorImpl implements  GetAllActivitiesInteractor{


    private GetAllActivitiesManagerImpl mManager;
    private Integer periodForUpdate;


    public GetAllActivitiesInteractorImpl(@NonNull GetAllActivitiesManagerImpl manager){
        this.mManager = manager;
    }


     @Override
    public void execute(final GetAllActivitiesInteractorCompletion completion,final GetAllActivitiesInteractorFailure failure) {

         mManager.getAllActivities(
                 new GetAllActivitiesManagerCompletion() {
                     @Override
                     public void completion(@NonNull List<ActivityEntity> activityEntities) {
                         completion.completion(MyActivitiesMapperFromEntities.mapperFromEntities(activityEntities ));
                     }
                 },
                 new GetAllActivitiesManagerFailure() {
                     @Override
                     public void onError(String errorDescription) {
                         Log.d("vamos", "Vamos");

                     }
                 });


    }



}
