package kc.dcava.domain.interactors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import kc.dcava.domain.managers.db.GetAllSaveActivitiesManager;
import kc.dcava.domain.managers.db.GetAllSaveActivitiesManagerCompletion;
import kc.dcava.domain.managers.db.GetAllSaveActivitiesManagerFailure;
import kc.dcava.domain.managers.network.GetRefreshImagesManager;
import kc.dcava.domain.managers.sharedpreferences.PostTimeLastUpdateManagerImpl;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public class GetRefreshImagesInteractorImpl implements  GetRefreshImagesInteractor{


    WeakReference<Context> weakContext;
    private static  int count = 0;

    public GetRefreshImagesInteractorImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }


    @Override
    public void execute(final LinkedList<MyActivity>  activityList, final Runnable completion) {


                count = 0;

        List<String> imagesToDownload = new ArrayList<String>();

        for (MyActivity act: activityList) {
            imagesToDownload.add( act.getLogo());
            imagesToDownload.add(    String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=800x300",
                    act.getLatitude(), act.getLongitude()));


        }

        final int i = imagesToDownload.size();

      /*  final ImageView dump[] = new ImageView[ activityList.size() ];

        for (int f=0;f< activityList.size();f++) {
            dump[f] = new ImageView(weakContext.get());
        }*/


                for ( int j=0; j< imagesToDownload.size(); j++) {
                    Picasso.with( weakContext.get() )
                            .load(  imagesToDownload.get(j) )
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .fetch(new Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.d("DAVID", "Entro onSuccess: " + count);
                                    count++;
                                    if(count>= i)
                                        new Thread(completion).start();

                                }

                                @Override
                                public void onError(){
                                    Log.d("DAVID", "Entro onError: " + count);
                                    count++;
                                    if(count>= i)
                                        new Thread(completion).start();

                                }
                            });
                            /*.into(dump[j], new Callback() {
                                @Override
                                public void onSuccess() {
                                    Log.d("DAVID", "Entro onSuccess: " + count);
                                    count++;
                                    if(count>= i)
                                        new Thread(completion).start();

                                }

                                @Override
                                public void onError(){
                                    Log.d("DAVID", "Entro onError: " + count);
                                    count++;
                                    if(count>= i)
                                            new Thread(completion).start();

                                }
                            });*/


                }





    }
}
