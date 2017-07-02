package kc.dcava.madridactivities.adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.LinkedList;

import kc.dcava.domain.model.MyActivity;
import kc.dcava.domain.model.MyActivity;
import kc.dcava.madridactivities.R;
import kc.dcava.madridactivities.fragment.ActivitiesListFragment;


public class ActivitiesViewAdapter extends RecyclerView.Adapter<ActivitiesViewAdapter.ActivitiesViewHolder> {

    private LinkedList<MyActivity> mMyActivities;
    private ActivitiesListFragment.OnClickRowMyActivityListerner mOnClickListener;
    WeakReference<Context> context;

    public ActivitiesViewAdapter(LinkedList<MyActivity> myActivities,  Context context) {
        super();
        mMyActivities = myActivities;
        this.context = new WeakReference<Context>(context);

    }

    public void setOnClickListener(ActivitiesListFragment.OnClickRowMyActivityListerner onClickRowMyActivityListerner) {
        mOnClickListener = onClickRowMyActivityListerner;
    }

    @Override
    public ActivitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_myactivity, parent, false);


       // view.setOnClickListener(mOnClickListener);

        return new ActivitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActivitiesViewHolder holder, int position) {
        holder.bindActivity(mMyActivities.get(position),position);
    }

    @Override
    public int getItemCount() {
        return  mMyActivities!= null ? mMyActivities.size() : 0;
    }

    protected class ActivitiesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageLogo;
        private final TextView mNameActivity;
        private Integer position;


        public ActivitiesViewHolder(View itemView) {
            super(itemView);
            mImageLogo = (ImageView) itemView.findViewById(R.id.myactivity_logo);
            mNameActivity = (TextView) itemView.findViewById(R.id.myactivity_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     mOnClickListener.onClickMyActivity( position );
                }
            });


        }

        public void bindActivity(MyActivity myActivity, int position) {

            Picasso.with(context.get())
                    .load(myActivity.getLogo())
                    .placeholder(R.drawable.bendito_ocio)
                    .networkPolicy(NetworkPolicy.OFFLINE)
                    .into(mImageLogo);

            this.position = position;

            mNameActivity.setText( myActivity.getName() );

        }


    }
}
