package kc.dcava.domain.managers.network;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import kc.dcava.domain.interactors.PostTimeLastUpdateInteractor;
import kc.dcava.domain.interactors.PostTimeLastUpdateInteractorImpl;
import kc.dcava.domain.managers.sharedpreferences.PostTimeLastUpdateManagerImpl;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 30/6/17.
 */

public class GetRefreshImagesManagerImpl {

    WeakReference<Context> weakContext;

    public GetRefreshImagesManagerImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }


    void refreshImagesFromServer(LinkedList<MyActivity>  activityList){




              /*  final int i = activityList.size();

                for ( int j=0; j<i; j++) {
                    ImageView dump = new ImageView( weakContext.get() );
                    Picasso.with( weakContext.get() )
                            .load(  activityList.get( j ).getLogo() )
                            .networkPolicy(NetworkPolicy.NO_CACHE)
                            .into(dump, new Callback() {

                                @Override
                                public void onSuccess() {
                                    count++;
                                    if(count>= i){

                                        new Thread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //le asignamos al fragment las actividades
                                                // completion();
                                            }
                                        }).start();

                                    }


                                }

                                @Override
                                public void onError() {
                                    count++;
                                    Log.d("DAVID","ERROR");
                                    if(count>= i){
                                        //le asignamos al fragment las actividades
                                        // completion();


                                    }
                                }
                            });

*/


    }
}
