package kc.dcava.domain.interactors;

import android.util.Log;

import java.util.LinkedList;

import kc.dcava.domain.managers.db.MyActivityDao;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManager;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManagerCompletion;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManagerFailure;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class PostAllSaveActivitiesInteractorImpl implements  PostAllSaveActivitiesInteractor {

    PostAllSaveActivitiesManager mManager;


    public PostAllSaveActivitiesInteractorImpl(PostAllSaveActivitiesManager Manager){
        super();
        this.mManager = Manager;
    }



    @Override
    public void execute(final LinkedList<MyActivity> myActivities, final PostAllSaveActivitiesInteractorCompletion completion, final PostAllSaveActivitiesInteractorFailure failure) {

        mManager.postAllSaveActivities(myActivities, new PostAllSaveActivitiesManagerCompletion() {
            @Override
            public void completion(Integer insertRowCount) {
                Log.d(this.getClass().getCanonicalName() ," inserted rows" + insertRowCount.toString());
                completion.completion();
            }
        }, new PostAllSaveActivitiesManagerFailure() {
            @Override
            public void onError(String errorDescription) {
                Log.e(this.getClass().getCanonicalName(), errorDescription);
                failure.onError(errorDescription);
            }
        });

    }


}
