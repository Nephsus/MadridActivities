package kc.dcava.madridactivities.activity;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import kc.dcava.domain.interactors.GetAllActivitiesInteractorImpl;
import kc.dcava.domain.interactors.GetAllActivitiesInteractor;
import kc.dcava.domain.interactors.GetAllActivitiesInteractorCompletion;
import kc.dcava.domain.interactors.GetAllActivitiesInteractorFailure;
import kc.dcava.domain.interactors.GetAllSaveActivitiesInteractor;
import kc.dcava.domain.interactors.GetAllSaveActivitiesInteractorCompletion;
import kc.dcava.domain.interactors.GetAllSaveActivitiesInteractorFailure;
import kc.dcava.domain.interactors.GetAllSaveActivitiesInteractorImpl;
import kc.dcava.domain.interactors.GetRefreshImagesInteractor;
import kc.dcava.domain.interactors.GetRefreshImagesInteractorImpl;
import kc.dcava.domain.interactors.GetTimeLastUpdateImplInteractor;
import kc.dcava.domain.interactors.GetTimeLastUpdateInteractor;
import kc.dcava.domain.interactors.PostAllSaveActivitiesInteractor;
import kc.dcava.domain.interactors.PostAllSaveActivitiesInteractorCompletion;
import kc.dcava.domain.interactors.PostAllSaveActivitiesInteractorFailure;
import kc.dcava.domain.interactors.PostAllSaveActivitiesInteractorImpl;
import kc.dcava.domain.interactors.PostTimeLastUpdateInteractor;
import kc.dcava.domain.interactors.PostTimeLastUpdateInteractorImpl;
import kc.dcava.domain.managers.db.GetAllSaveActivitiesManagerImpl;
import kc.dcava.domain.managers.db.PostAllSaveActivitiesManagerImpl;
import kc.dcava.domain.managers.network.GetAllActivitiesManagerImpl;
import kc.dcava.domain.managers.network.GetRefreshImagesManager;
import kc.dcava.domain.managers.network.NetworkUtils;
import kc.dcava.domain.managers.sharedpreferences.GetTimeLastUpdateManagerImpl;
import kc.dcava.domain.managers.sharedpreferences.PostTimeLastUpdateManagerImpl;
import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.fragment.ActivitiesListFragment;
import kc.dcava.madridactivities.fragment.ActivityDetailFragment;
import kc.dcava.madridactivities.steps.Steps;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static kc.dcava.madridactivities.util.Utilities.centerMapInPosition;

public class MainActivity extends ParentMadridActivity {

    private static final int TAG_ACCESS_FINE_AND_COARSE = 67;
    public GoogleMap mMap;
    private MapFragment mMapFragment;

    private SearchView mSearchView;

    private static  int count = 0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.showProgressDialog(this);

        mMapFragment = MapFragment.newInstance();
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_map, mMapFragment, "map");
        fragmentTransaction.commit();



        GetTimeLastUpdateInteractor getTimeLastUpdateInteractor = new GetTimeLastUpdateImplInteractor(new GetTimeLastUpdateManagerImpl(this), 7);



        getTimeLastUpdateInteractor.execute(getResources().getString(R.string.config_file), getResources().getString(R.string.last_update),new Runnable() {
            @Override
            public void run() {
                loadCacheMyActivities(  );
            }
        }, new Runnable() {
            @Override
            public void run() {
                if(!NetworkUtils.isConnected( MainActivity.this )){
                         MainActivity.this.runOnUiThread(new Runnable() {
                             @Override
                             public void run() {
                                 showErrorConnection();
                             }
                         });

                }else {
                    loadNetWorkMyActivities();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

         mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();

        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setIconifiedByDefault(false);


        return true;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection

       if (item.getItemId() == R.id.search) {
           mSearchView.requestFocus();
           ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                   toggleSoftInput(InputMethodManager.SHOW_FORCED,
                           InputMethodManager.HIDE_IMPLICIT_ONLY);
       }
        return true;
    }



    private void loadCacheMyActivities( ) {
        Log.d("MainActivity", "loadCacheMyActivities");
        String query = null;
        this.dismissProgressDialog();

        GetAllSaveActivitiesInteractor getAllSaveActivitiesInteractor = new GetAllSaveActivitiesInteractorImpl(new GetAllSaveActivitiesManagerImpl(this));
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            query = getIntent().getStringExtra(SearchManager.QUERY);
        }

        getAllSaveActivitiesInteractor.execute(query,new GetAllSaveActivitiesInteractorCompletion() {
            @Override
            public void completion(final LinkedList<MyActivity> myActivities) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        loadFragmentList(myActivities);
                        loadingMap(myActivities);
                    }});
            }
        }, new GetAllSaveActivitiesInteractorFailure() {
            @Override
            public void onError(String errorDescription) {
                MainActivity.this.dismissProgressDialog();
            }
        });


    }

    private void loadNetWorkMyActivities() {
        Log.d("MainActivity", "loadNetWorkMyActivities");
        GetAllActivitiesInteractor getAllActivitiesManager = new GetAllActivitiesInteractorImpl(new GetAllActivitiesManagerImpl(this));

        //Comprobamos si hay conexión a internet. Si no hay avisamos con un



           getAllActivitiesManager.execute(new GetAllActivitiesInteractorCompletion() {
               @Override
               public void completion(@NonNull final LinkedList<MyActivity> activityList) {


                   //Salvamos las actividades en bbdd
                   PostAllSaveActivitiesInteractor postAllSaveActivitiesInteractor = new PostAllSaveActivitiesInteractorImpl(new PostAllSaveActivitiesManagerImpl(MainActivity.this));

                   postAllSaveActivitiesInteractor.execute(activityList, new PostAllSaveActivitiesInteractorCompletion() {
                       @Override
                       public void completion() {

                           final GetRefreshImagesInteractor getRefreshImagesManager = new GetRefreshImagesInteractorImpl(MainActivity.this);

                           MainActivity.this.runOnUiThread(new Runnable() {
                               @Override
                               public void run() {

                                   getRefreshImagesManager.execute(activityList, new Runnable() {
                                       @Override
                                       public void run() {
                                           //le asignamos al fragment las actividades
                                           loadFragmentList(activityList);
                                           loadingMap(activityList);
                                           //Salvamos la fecha para proximas actualizaciones
                                           PostTimeLastUpdateInteractor postTimeLastUpdateInteractor = new PostTimeLastUpdateInteractorImpl(new PostTimeLastUpdateManagerImpl(MainActivity.this));
                                           postTimeLastUpdateInteractor.execute(getResources().getString(R.string.config_file), getResources().getString(R.string.last_update), new SimpleDateFormat("yyyyMMdd").format(new Date()));
                                           MainActivity.this.dismissProgressDialog();
                                       }
                                   });
                               }
                           });

                       }
                   }, new PostAllSaveActivitiesInteractorFailure() {
                       @Override
                       public void onError(String errorDescription) {
                           MainActivity.this.dismissProgressDialog();
                       }
                   });

               }
           }, new GetAllActivitiesInteractorFailure() {
               @Override
               public void onError(String errorDescription) {
                   MainActivity.this.dismissProgressDialog();
               }
           });


    }

    private void showErrorConnection() {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.errorConnectionText))
                .setTitle(getResources().getString(R.string.errorConnectionTitle))
                .setPositiveButton(getResources().getString(R.string.errorConnectionButton), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                });
        // Create the AlertDialog object and return it
         builder.create().show();



    }

    private void loadFragmentList(LinkedList<MyActivity> activityList) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.findFragmentById(R.id.fragment_container) == null) {

            ActivitiesListFragment df = ActivitiesListFragment.newInstance(activityList);
            fragmentManager.beginTransaction().add(R.id.fragment_container, df).commit();

        }


    }

    private void loadingMap(final LinkedList<MyActivity> activityList) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragment_container_map);

                mapFragment.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        if (googleMap == null) {
                            Toast.makeText(getApplicationContext(),
                                    "Error no hay mapas dispnibles", Toast.LENGTH_SHORT)
                                    .show();
                        } else {
                            mMap = googleMap;
                            configureDataToMap();
                            addActivitiesToMap(activityList);
                        }
                    }
                });

            }
        });

    }

    public void configureDataToMap() {
        if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    TAG_ACCESS_FINE_AND_COARSE);

            return;
        }
        centerMapInPosition(mMap, 40.411335, -3.674908);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(MAP_TYPE_NORMAL);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setMyLocationEnabled(true);


    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case TAG_ACCESS_FINE_AND_COARSE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    configureDataToMap();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    private void addActivitiesToMap(LinkedList<MyActivity> activityList) {

        if (activityList!=null &&  activityList.size() >0 )
            for (MyActivity myActivity: activityList) {
             MarkerOptions  markerOptions = new MarkerOptions()
                    .position(new LatLng(myActivity.getLatitude(), myActivity.getLongitude()))
                    .title(myActivity.getName()).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE));

             mMap.addMarker(markerOptions).setTag( myActivity );


        }


        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if (marker.getTag() == null || !(marker.getTag() instanceof MyActivity)) {
                    return;
                }
            Steps.goToDetailActivity(MainActivity.this, (MyActivity) marker.getTag());


            }
        });

    }






}

