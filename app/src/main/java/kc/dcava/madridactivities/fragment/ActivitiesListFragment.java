package kc.dcava.madridactivities.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.activity.MainActivity;
import kc.dcava.madridactivities.adapters.ActivitiesViewAdapter;
import kc.dcava.madridactivities.steps.Steps;


public class ActivitiesListFragment extends Fragment {

    private static final String ACTIVITIES_LIST = "ACTIVITIES_LIST";
    private Integer ActivitiesListFragment;
    private LinkedList<MyActivity> mMyActivityList;

    @BindView(R.id.fragment_lists_all_myactivities) RecyclerView recycler;


    public static ActivitiesListFragment newInstance(LinkedList<MyActivity> activityList) {
        ActivitiesListFragment fragment = new ActivitiesListFragment();

        Bundle args = new Bundle();
        args.putSerializable(ACTIVITIES_LIST, activityList);
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

        mMyActivityList = (LinkedList<MyActivity> ) this.getArguments().getSerializable( ACTIVITIES_LIST );

        View root = inflater.inflate(R.layout.fragment_list_activities, container, false);
        ButterKnife.bind(this, root);

        recycler = (RecyclerView) root.findViewById(R.id.fragment_lists_all_myactivities);

        ActivitiesViewAdapter adapter = new ActivitiesViewAdapter(mMyActivityList,  getActivity() );
        recycler.setAdapter( adapter );
        recycler.setLayoutManager( new LinearLayoutManager( getActivity()));

        adapter.setOnClickListener(new OnClickRowMyActivityListerner() {
            @Override
            public void onClickMyActivity(int position) {
                Steps.goToDetailActivity( (MainActivity)getActivity(), mMyActivityList.get( position ));

            }
        });





        return root;
    }


    public interface OnClickRowMyActivityListerner{
           void onClickMyActivity(int position);

    }




}
