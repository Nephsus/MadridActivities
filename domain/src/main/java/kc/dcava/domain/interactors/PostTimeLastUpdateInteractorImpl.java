package kc.dcava.domain.interactors;

import kc.dcava.domain.managers.db.GetAllSaveActivitiesManager;
import kc.dcava.domain.managers.sharedpreferences.PostTimeLastUpdateManager;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public class PostTimeLastUpdateInteractorImpl implements  PostTimeLastUpdateInteractor {

    PostTimeLastUpdateManager mManager;


    public PostTimeLastUpdateInteractorImpl(PostTimeLastUpdateManager Manager){
        super();
        this.mManager = Manager;
    }

    @Override
    public void execute(String filename, String key,String data){
        mManager.saveDataInConfigFile( filename,  key, data );
    }
}
