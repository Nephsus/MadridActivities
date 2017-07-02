package kc.dcava.madridactivities.activity;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import kc.dcava.madridactivities.R;


public class ParentMadridActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;



    protected void showProgressDialog(final Context context){


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("Padre", Thread.currentThread().getName());
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                mProgressDialog.setProgressStyle(android.R.attr.spinnerStyle);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                mProgressDialog.setContentView(R.layout.progress_layout);
            }
        });

    }

    protected void dismissProgressDialog(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Log.d("Padre2222", Thread.currentThread().getName());
                mProgressDialog.dismiss();
            }
        });

    }





}
