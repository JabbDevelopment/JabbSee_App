package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;

import java.util.ArrayList;
import java.util.List;

public class SerieEditActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + SerieEditActivity.class.getSimpleName();

    TextView editTextInput;
    Button saveButton;
    List<String> serieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_edit);
        editTextInput = findViewById(R.id.editTextInput);
        saveButton = findViewById(R.id.saveButton);
        serieList = new ArrayList<>();

        editSerie();
    }

    public void editSerie(){

        Bundle bundle = getIntent().getExtras();

        String serieTitle = bundle.getString("com.jabb.SERIE_INDEX");

        editTextInput.setText(serieTitle);

        saveSerie();
    }

    public void saveSerie(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String title = String.valueOf(serieList.add(editTextInput.getText().toString()));
                Log.d(TAG, "serieList " + serieList);
                Intent homeActivity = new Intent(getApplication(), HomeActivity.class);
                homeActivity.putExtra("com.jabb.SERIE_INDEX", title);
                startActivity(homeActivity);

            }
        });


    }
}
