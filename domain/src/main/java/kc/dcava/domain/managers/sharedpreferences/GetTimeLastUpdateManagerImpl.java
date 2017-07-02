package kc.dcava.domain.managers.sharedpreferences;

import android.content.Context;
import java.lang.ref.WeakReference;

/**
 * Created by davidcavajimenez on 25/6/17.
 */

public class GetTimeLastUpdateManagerImpl  implements  GetTimeLastUpdateManager {

    private WeakReference<Context> contextWeakReference;

    public GetTimeLastUpdateManagerImpl(Context contextWeakReference) {
        this.contextWeakReference = new WeakReference<Context>(contextWeakReference);
    }

    @Override
    public String execute(String filename, String key){
        return contextWeakReference.get().getSharedPreferences(filename,
                                            contextWeakReference.get().MODE_PRIVATE).getString(key, "99999999");

    }


}
