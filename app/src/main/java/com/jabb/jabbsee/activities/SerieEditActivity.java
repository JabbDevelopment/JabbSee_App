package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.helpers.SerieListHelper;
import com.jabb.jabbsee.models.Serie;

import java.io.IOException;

public class SerieEditActivity extends AppCompatActivity {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + SerieEditActivity.class.getSimpleName();

    private TextView titleEditText;
    private TextView seasonEditText;
    private TextView episodeEditText;
    private Serie serie;
    private int serieIndex;

    private Button saveButton;
    //private List<String> serieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_edit);
        titleEditText = findViewById(R.id.titleEditText);
        seasonEditText = findViewById(R.id.seasonEditText);
        episodeEditText = findViewById(R.id.episodeEditText);


        saveButton = findViewById(R.id.saveButton);
        //serieList = new ArrayList<>();

        implementSerie();
        setOnSave();
    }

    public void implementSerie(){

        Bundle bundle = getIntent().getExtras();

        serie = (Serie)bundle.getSerializable(Constants.SERIE_KEY);
        serieIndex = bundle.getInt(Constants.SERIE_INDEX_KEY);

        String serieTitle = serie.getTitle();
        String serieSeason = Integer.toString(serie.getSeason());
        String serieEpisode = Integer.toString(serie.getEpisode());

        titleEditText.setText(serieTitle);
        seasonEditText.setText(serieSeason);
        episodeEditText.setText(serieEpisode);

    }

    public void setOnSave(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String title = String.valueOf(serieList.add(titleEditText.getText().toString()));
                //Log.d(TAG, "serieList " + serieList);
                String title = titleEditText.getText().toString();
                String seasonAsString = seasonEditText.getText().toString();
                String episodeAsString = episodeEditText.getText().toString();
                int season = Integer.parseInt(seasonAsString);
                int episode = Integer.parseInt(episodeAsString);

                serie.setTitle(title);
                serie.setSeason(season);
                serie.setEpisode(episode);

                new updateLibrary().execute();



            }
        });


    }


    private class updateLibrary extends AsyncTask<Void, Void, Boolean> {

        public updateLibrary(){}

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Log.d(TAG, "Do in background. Updating library");
            try {
                SerieListHelper.getInstance().updateSerie(serie, serieIndex);
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

            Intent homeActivity = new Intent(getApplication(), HomeActivity.class);
            //homeActivity.putExtra("com.jabb.SERIE_INDEX", title);
            startActivity(homeActivity);


        }
    }

}
