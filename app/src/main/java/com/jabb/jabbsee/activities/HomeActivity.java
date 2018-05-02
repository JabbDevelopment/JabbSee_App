package com.jabb.jabbsee.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;


public class HomeActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d(TAG,"Logging in HomeActivity");

        Toolbar toolBar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setIcon(getDrawable(R.drawable.profile));
        

    }

}
