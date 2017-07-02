package kc.dcava.madridactivities.steps;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.activity.DetailActivity;
import kc.dcava.madridactivities.activity.MainActivity;
import kc.dcava.madridactivities.activity.ParentMadridActivity;
import kc.dcava.madridactivities.fragment.ActivityDetailFragment;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class Steps {


    public static void goToDetailActivity( final MainActivity origin, MyActivity myActivity){

        Intent intent = new Intent(origin, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(DetailActivity.SELECTED_MYACTIVITY, myActivity); //Your id
        intent.putExtras(bundle); //Put your id to your next Intent
        origin.startActivity(intent);


    }

}
