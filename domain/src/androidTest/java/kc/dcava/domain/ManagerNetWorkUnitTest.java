package kc.dcava.domain;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import kc.dcava.domain.managers.network.GetAllActivitiesManager;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerCompletion;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerFailure;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerImpl;
import kc.dcava.domain.managers.network.NetworkUtils;
import kc.dcava.domain.managers.network.entities.ActivityEntity;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ManagerNetWorkUnitTest {

    private static Context appContext = InstrumentationRegistry.getTargetContext();

    @Test
    public void download_all_activities_from_network_server() throws Exception {
        GetAllActivitiesManager<GetAllActivitiesManagerCompletion, GetAllActivitiesManagerFailure> getAllActivitiesManager = new GetAllActivitiesManagerImpl(appContext);

        getAllActivitiesManager.getAllActivities(new GetAllActivitiesManagerCompletion() {
            @Override
            public void completion(@NonNull List<ActivityEntity> activityEntities) {
                    assertEquals(activityEntities.size(), 14);
            }
        }, new GetAllActivitiesManagerFailure() {
            @Override
            public void onError(String errorDescription) {
                System.err.print("ERROR:" + errorDescription);
                assertEquals(2, 1);
            }
        });


    }

    @Test
    public void check_conectivity() throws Exception {
       assertTrue( NetworkUtils.isWifiConnected( appContext ) );

    }



}



