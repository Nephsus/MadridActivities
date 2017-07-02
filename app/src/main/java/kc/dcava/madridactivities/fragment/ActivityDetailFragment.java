package kc.dcava.madridactivities.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.Locale;

import butterknife.ButterKnife;
import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.adapters.ActivitiesViewAdapter;
import kc.dcava.madridactivities.util.Utilities;

/**
 * Created by davidcavajimenez on 29/6/17.
 */

public class ActivityDetailFragment  extends Fragment{


    private static final String ACTIVITY_SELECTED = "ACTIVITY_SELECTED";
    private MyActivity mMyActivity;
    private ImageView mLogo;
    private TextView  mName;
    private TextView  mDescription;
    private TextView  mAddress;
    private ImageView mMap;


    public static ActivityDetailFragment newInstance(MyActivity myActivity) {
        ActivityDetailFragment fragment = new ActivityDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ACTIVITY_SELECTED, myActivity);
        fragment.setArguments(args);
        return fragment;

    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMyActivity = (MyActivity) this.getArguments().getSerializable( ACTIVITY_SELECTED );

        View root = inflater.inflate(R.layout.fragment_detail_activity, container, false);


        mLogo = (ImageView) root.findViewById(R.id.myactivity_logo__detail);
        mName = (TextView) root.findViewById(R.id.myactivity_name__detail);
        mDescription = (TextView) root.findViewById(R.id.myactivity_description__detail);
        mAddress = (TextView) root.findViewById(R.id.myactivity_address__detail);

        mMap = (ImageView) root.findViewById(R.id.image_map_location);

        Picasso.with(getActivity())
                .load(mMyActivity.getLogo())
                .placeholder(R.drawable.bendito_ocio)
                .into(mLogo);


        Picasso.with(getActivity())
                .load(Utilities.getMapImageUrl(mMyActivity))
                .placeholder(R.drawable.bendito_ocio)
                .into(mMap);

        bindView();

        return root;
    }


    private void  bindView(){

        mName.setText(mMyActivity.getName());
        mAddress.setText(mMyActivity.getAddress());


        if( getResources().getString(R.string.defaultLanguage).equals(Locale.getDefault().getLanguage())  )
            mDescription.setText(mMyActivity.getDescription_es());
        else
            mDescription.setText(mMyActivity.getDescription_en());

    }
}
