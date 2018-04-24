package com.jabb.jabbsee;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivityJava extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabItemActive;
    ListView serieListView;
    String[] series;
    String[] seasons;
    String[] episodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        Resources res = getResources();
        serieListView = findViewById(R.id.SerieListView);
        series = res.getStringArray(R.array.series);
        seasons = res.getStringArray(R.array.seasons);
        episodes = res.getStringArray(R.array.episodes);
        tabLayout = findViewById(R.id.tabLayout);
        tabItemActive = findViewById(R.id.activeTabItem);

        SerieAdapter serieAdapter = new SerieAdapter(this, series,seasons,episodes);
        serieListView.setAdapter(serieAdapter);

        serieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent showSerieSelectedActivity = new Intent(getApplicationContext(), SerieSelectedActivity.class);
                showSerieSelectedActivity.putExtra("om.jabb.SERIE_INDEX", position);
                startActivity(showSerieSelectedActivity);
            }
        });

    }
}
