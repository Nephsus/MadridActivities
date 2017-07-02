package kc.dcava.madridactivities;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.res.Resources;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.squareup.picasso.Picasso;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class MadridActivitiesInitiator extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Picasso.with(getApplicationContext()).setIndicatorsEnabled( true );
        Picasso.with(getApplicationContext()).setLoggingEnabled( true );

        Log.d("EA","EA");

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }


}
