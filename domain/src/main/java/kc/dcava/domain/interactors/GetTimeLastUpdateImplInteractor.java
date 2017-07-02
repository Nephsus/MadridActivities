package kc.dcava.domain.interactors;

import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import kc.dcava.domain.managers.sharedpreferences.GetTimeLastUpdateManager;

/**
 * Created by davidcavajimenez on 25/6/17.
 */

public class GetTimeLastUpdateImplInteractor  implements  GetTimeLastUpdateInteractor {

    private GetTimeLastUpdateManager mManager;
    private Integer periodForUpdate;


    public GetTimeLastUpdateImplInteractor(@NonNull GetTimeLastUpdateManager manager, Integer periodForUpdate){
        this.mManager = manager;
        this.periodForUpdate = periodForUpdate;
    }

    @Override
    public void execute(String filename, String key, Runnable activitiesAreCached, Runnable activitiesAreNotCached) {
      Integer lastUpdate =  Integer.parseInt( mManager.execute(filename , key ) ) ;

      if( (lastUpdate  - getLocalDateInDevice() ) < periodForUpdate ){

          new Thread( activitiesAreCached ).start();
      }
      else{
          new Thread( activitiesAreNotCached ) .start();
      }

    }


    private Integer getLocalDateInDevice(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String currentDate = sdf.format(new Date());

        return Integer.parseInt( currentDate );

    }

}
