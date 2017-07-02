package kc.dcava.domain.managers.network.parsers;

import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kc.dcava.domain.managers.network.entities.ActivityEntity;
import kc.dcava.domain.model.MyActivity;

import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_ADDRESS;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_DESCRIPTION_EN;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_DESCRIPTION_ES;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_IMAGE_URL;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LATITUDE;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LOGO_IMAGE_URL;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_LONGITUDE;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_NAME;
import static kc.dcava.domain.managers.db.DBConstants.KEY_MYACTIVITY_URL;

/**
 * Created by davidcavajimenez on 28/6/17.
 */

public class MyActivitiesMapperFromEntities {


    public static LinkedList<MyActivity> mapperFromEntities(List<ActivityEntity> myEntities) {

        LinkedList<MyActivity> result = new LinkedList<>();

        for (ActivityEntity act : myEntities) {
            MyActivity myActivity = new MyActivity();

            myActivity.setAddress( act.getAddress() )
                    .setDescription_en( act.getDescription_en() )
                    .setDescription_es( act.getDescription_es() )
                    .setFront_image( act.getImg() )
                    .setName( act.getName() )
                    .setLogo( act.getLogo_img() )
                    .setLatitude(getCorrectCoordinateComponent( act.getGps_lat() ))
                    .setLongitude(getCorrectCoordinateComponent( act.getGps_lon() ));

            result.add( myActivity );

        }

        return  result;
    }

    public static float getCorrectCoordinateComponent(String coordinateComponent) {
        // Este método lo reaprovecho diego....I'm sorry
        float coordinate = 0.0f;

        String s = coordinateComponent.replace(",", "");
        try {
            coordinate = Float.parseFloat(s);
        } catch (Exception e) {
            Log.d("ERROR CONVERTING", String.format("Can't convert %s", coordinateComponent));
        }
        return coordinate;
    }



    public static ContentValues mapperToDB(MyActivity myActivity) {

        final ContentValues contentValues = new ContentValues();


        contentValues.put(KEY_MYACTIVITY_NAME, myActivity.getName());
        contentValues.put(KEY_MYACTIVITY_ADDRESS, myActivity.getAddress());
        contentValues.put(KEY_MYACTIVITY_DESCRIPTION_ES, myActivity.getDescription_es());
        contentValues.put(KEY_MYACTIVITY_DESCRIPTION_EN, myActivity.getDescription_en());
        contentValues.put(KEY_MYACTIVITY_IMAGE_URL, myActivity.getFront_image());
        contentValues.put(KEY_MYACTIVITY_LOGO_IMAGE_URL, myActivity.getLogo());
        contentValues.put(KEY_MYACTIVITY_LATITUDE, myActivity.getLatitude());
        contentValues.put(KEY_MYACTIVITY_LONGITUDE, myActivity.getLongitude());

        return contentValues;


    }


}
