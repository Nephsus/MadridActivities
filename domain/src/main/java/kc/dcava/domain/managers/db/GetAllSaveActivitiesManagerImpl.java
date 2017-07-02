package kc.dcava.domain.managers.db;

import android.content.Context;

import java.lang.ref.WeakReference;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class GetAllSaveActivitiesManagerImpl implements GetAllSaveActivitiesManager {

    private WeakReference<Context> contextWeakReference;

    public GetAllSaveActivitiesManagerImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }


   public void getAllSaveActivitiesManagerImpl(final String query, final GetAllSaveActivitiesManagerCompletion completion, final GetAllSaveActivitiesManagerFailure failure ){

       new Thread(new Runnable() {
           @Override
           public void run() {
               MyActivityDao < MyActivity > myActivityDao = new MyActivityDaoImpl(contextWeakReference.get());


               if( query !=null)
                   completion.completion( myActivityDao.query("UPPER(" + DBConstants.KEY_MYACTIVITY_NAME + ") LIKE ?", new String[]{"%"+ query+"%" }, DBConstants.KEY_MYACTIVITY_ID));
               else{
                   completion.completion(myActivityDao.query());
               }
           }
       }).start();

   }

}
