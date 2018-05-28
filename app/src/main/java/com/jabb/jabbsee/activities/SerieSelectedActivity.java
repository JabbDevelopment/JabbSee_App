package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.models.Serie;

public class SerieSelectedActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + SerieSelectedActivity.class.getSimpleName();

    private ImageView pencil;
    private TextView serieSelectedTitleView;
    private TextView serieSelectedSeasonView;
    private TextView serieSelectedEpisodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_selected);
        pencil = findViewById(R.id.pencilImageView);
        selectSerie();
    }

    public void selectSerie(){
        serieSelectedTitleView = findViewById(R.id.serieSelectedTitleView);
        serieSelectedSeasonView = findViewById(R.id.serieSelectedSeasonView);
        serieSelectedEpisodeView = findViewById(R.id.serieSelectedEpisodeView);


        Bundle bundle = getIntent().getExtras();


        Serie serie = (Serie)bundle.getSerializable(Constants.SERIE_KEY);
        int serieIndex = bundle.getInt(Constants.SERIE_INDEX_KEY);
        Log.d(TAG, "Serie from bundle: Title: " + serie.getTitle() + " Season: " + serie.getSeason());


        String serieTitle = serie.getTitle();
        String serieSeason = "Season: " + Integer.toString(serie.getSeason());
        String serieEpisode = "Episode: " + Integer.toString(serie.getEpisode());
        serieSelectedTitleView.setText(serieTitle);
        serieSelectedSeasonView.setText(serieSeason);
        serieSelectedEpisodeView.setText(serieEpisode);
        pressPencil(serie, serieIndex);
    }

    public void pressPencil(final Serie serie, final int serieIndex){

        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent serieEditActivity = new Intent(getApplication(), SerieEditActivity.class);
                serieEditActivity.putExtra(Constants.SERIE_KEY, serie);
                serieEditActivity.putExtra(Constants.SERIE_INDEX_KEY, serieIndex);
                startActivity(serieEditActivity);
            }
        });
    }
}
