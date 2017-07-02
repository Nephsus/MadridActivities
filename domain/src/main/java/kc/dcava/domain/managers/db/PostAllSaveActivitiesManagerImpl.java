package kc.dcava.domain.managers.db;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class PostAllSaveActivitiesManagerImpl implements PostAllSaveActivitiesManager{

    private WeakReference<Context> contextWeakReference;

    public PostAllSaveActivitiesManagerImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void postAllSaveActivities(final LinkedList<MyActivity> myActivities, final PostAllSaveActivitiesManagerCompletion completion, final PostAllSaveActivitiesManagerFailure failure){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MyActivityDao<MyActivity> myActivityDao = new MyActivityDaoImpl(contextWeakReference.get());

                    myActivityDao.deleteAll(); //Borramos todo antes de insertar, esto lo podía haber puesto en otro interactor pero bueno....

                    for (MyActivity myActivity : myActivities) {
                        myActivityDao.insert(myActivity);
                    }

                    completion.completion( myActivities.size() );
                }catch (Exception e){
                    failure.onError( e.getMessage() );
                }
            }
        }).start();




    }





}
