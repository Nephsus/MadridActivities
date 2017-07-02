package kc.dcava.domain.parsers.jsonparser;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import kc.dcava.domain.managers.network.entities.ActivityEntity;
import kc.dcava.domain.managers.network.entities.ActivityResponseEntity;
import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 27/6/17.
 */

public class ActivityJsonParser {

    public List<ActivityEntity> parse(@NonNull final String response) {
        if (response == null) {
            return null;
        }

        List<ActivityEntity> activityEntities = null;

        try {
            Gson gson = new GsonBuilder().create();

            Reader reader = new StringReader(response);
            ActivityResponseEntity activityResponseEntity = gson.fromJson(reader, ActivityResponseEntity.class);
            activityEntities = activityResponseEntity.getResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return activityEntities;
    }
}
