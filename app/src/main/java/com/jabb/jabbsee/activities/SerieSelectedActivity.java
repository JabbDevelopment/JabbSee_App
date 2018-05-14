package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;

public class SerieSelectedActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + SerieSelectedActivity.class.getSimpleName();

    private ImageView pencil;
    TextView serieSelectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_selected);
        pencil = findViewById(R.id.pencilImageView);
        selectSerie();

    }

    public void selectSerie(){
        serieSelectedTextView = findViewById(R.id.serieSelectedTextView);

        Bundle bundle = getIntent().getExtras();

        String serieTitle = bundle.getString("com.jabb.SERIE_INDEX");

        serieSelectedTextView.setText(serieTitle);
        pressPencil(serieTitle);

    }

    public void pressPencil(final String serieTitle){

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent serieEditActivity = new Intent(getApplication(), SerieEditActivity.class);
                serieEditActivity.putExtra("com.jabb.SERIE_INDEX", serieTitle);
                startActivity(serieEditActivity);
            }
        });
    }
}
