package kc.dcava.madridactivities.activity;

import android.os.Bundle;

import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.fragment.ActivityDetailFragment;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class DetailActivity extends ParentMadridActivity {

    public static final String SELECTED_MYACTIVITY = "SELECTED_MYACTIVITY";
    private MyActivity mMyActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        mMyActivity = (MyActivity) bundle.getSerializable(SELECTED_MYACTIVITY);

        getFragmentManager().beginTransaction().add(R.id.fragment_activity_detail, ActivityDetailFragment.newInstance( mMyActivity )).commit();



    }
}
