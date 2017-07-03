package kc.dcava.domain.managers.network;

import android.content.Context;
import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.lang.ref.WeakReference;
import java.util.List;
import kc.dcava.domain.managers.network.entities.ActivityEntity;
import kc.dcava.domain.parsers.jsonparser.ActivityJsonParser;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public class GetAllActivitiesManagerImpl implements GetAllActivitiesManager<GetAllActivitiesManagerCompletion,GetAllActivitiesManagerFailure> {


    WeakReference<Context> weakContext;

    public GetAllActivitiesManagerImpl(Context context) {
        weakContext = new WeakReference<Context>(context);
    }


    @Override
    public void getAllActivities(final GetAllActivitiesManagerCompletion completion, final GetAllActivitiesManagerFailure failure) {

        String url = "http://madrid-shops.com/json_new/getActivities.php";

        RequestQueue queue = Volley.newRequestQueue(weakContext.get());

        StringRequest request = new StringRequest(
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("JSON", response);

                        ActivityJsonParser parser = new ActivityJsonParser();
                        List<ActivityEntity> entities = parser.parse(response);






                        if (completion != null) {
                            completion.completion( entities );
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("JSON", error.toString());
                        if (failure != null) {
                            failure.onError(error.getMessage());
                        }
                    }
                }
        );
        queue.add(request);





    }
}
