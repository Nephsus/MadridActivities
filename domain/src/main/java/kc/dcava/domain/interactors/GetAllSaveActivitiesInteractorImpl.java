package kc.dcava.domain.interactors;

import android.util.Log;

import java.util.LinkedList;

import kc.dcava.domain.managers.db.GetAllSaveActivitiesManager;
import kc.dcava.domain.managers.db.GetAllSaveActivitiesManagerCompletion;
import kc.dcava.domain.managers.db.GetAllSaveActivitiesManagerFailure;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManager;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManagerCompletion;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManagerFailure;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class GetAllSaveActivitiesInteractorImpl implements  GetAllSaveActivitiesInteractor {

    GetAllSaveActivitiesManager mManager;


    public GetAllSaveActivitiesInteractorImpl(GetAllSaveActivitiesManager Manager){
        super();
        this.mManager = Manager;
    }


    @Override
    public void execute(final String query, final GetAllSaveActivitiesInteractorCompletion completion, final GetAllSaveActivitiesInteractorFailure failure) {
        mManager.getAllSaveActivitiesManagerImpl(query,new GetAllSaveActivitiesManagerCompletion() {
            @Override
            public void completion(LinkedList<MyActivity> myActivities) {
                completion.completion( myActivities );
            }
        }, new GetAllSaveActivitiesManagerFailure() {
            @Override
            public void onError(String errorDescription) {
                failure.onError( errorDescription );
            }
        });

    }
}
