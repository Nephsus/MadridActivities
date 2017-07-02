package kc.dcava.madridactivities.util;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import kc.dcava.domain.model.MyActivity;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class Utilities {

    public static String getMapImageUrl(@NonNull final MyActivity myActivity) {
        String url = String.format("http://maps.googleapis.com/maps/api/staticmap?center=%f,%f&zoom=17&size=800x300",
                myActivity.getLatitude(), myActivity.getLongitude());

        return url;
    }

    public static void centerMapInPosition(GoogleMap googleMap, double latitude, double longitude) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(latitude, longitude)).zoom(10).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }




}
