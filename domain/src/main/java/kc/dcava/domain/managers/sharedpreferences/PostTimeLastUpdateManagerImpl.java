package kc.dcava.domain.managers.sharedpreferences;

import android.content.Context;
import java.lang.ref.WeakReference;


/**
 * Created by davidcavajimenez on 30/6/17.
 */

public class PostTimeLastUpdateManagerImpl  implements PostTimeLastUpdateManager  {


    private WeakReference<Context> contextWeakReference;

    public PostTimeLastUpdateManagerImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public void saveDataInConfigFile(String filename, String key, String data){

         contextWeakReference.get()
                .getSharedPreferences( filename,contextWeakReference.get().MODE_PRIVATE)
                    .edit().putString( key, data )
                        .commit();

    }
}
