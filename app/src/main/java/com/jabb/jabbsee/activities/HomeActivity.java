package com.jabb.jabbsee.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.helpers.SerieListHelper;

import java.io.IOException;


public class HomeActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new getLibrary().execute();

    }

    private class getLibrary extends AsyncTask<Void, Void, Boolean> {

        public getLibrary(){}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.d(TAG, "Do in background");
            try {
                SerieListHelper.getInstance().updateSerieList();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            //progressBar.setVisibility(View.GONE);

            if(!success){
                Log.e(TAG, "Failed to connect to server");
                //new showConnectionError(charSequence, getActivity()).showUnableToConnectDialog();
            }

            setContentView(R.layout.activity_home);

            Log.d(TAG,"Logging in HomeActivity");

            Toolbar toolBar = findViewById(R.id.customToolbar);
            setSupportActionBar(toolBar);
            getSupportActionBar().setTitle("");
            getSupportActionBar().setIcon(getDrawable(R.drawable.profile));

        }
    }



}
