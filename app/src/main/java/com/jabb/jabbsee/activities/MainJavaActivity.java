package com.jabb.jabbsee.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jabb.jabbsee.R;
import com.jabb.jabbsee.adapters.SerieAdapter;
import com.jabb.jabbsee.adapters.TabSectionsPageAdapter;
import com.jabb.jabbsee.fragments.TabActiveFragment;
import com.jabb.jabbsee.fragments.TabAllFragment;
import com.jabb.jabbsee.fragments.TabTipFragment;


public class MainJavaActivity extends AppCompatActivity {

    TabSectionsPageAdapter mTabSectionsPageAdapter;
    private ViewPager mViewPager;

    private ListView serieListView;
    private String[] series;
    private String[] seasons;
    private String[] episodes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);

        mTabSectionsPageAdapter = new TabSectionsPageAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.viewContainer);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        Toolbar toolBar = findViewById(R.id.customToolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setIcon(getDrawable(R.drawable.profile));

        Resources res = getResources();
        serieListView = findViewById(R.id.serieListView);
        series = res.getStringArray(R.array.series);
        seasons = res.getStringArray(R.array.seasons);
        episodes = res.getStringArray(R.array.episodes);

        SerieAdapter serieAdapter = new SerieAdapter(this, series, seasons, episodes);
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

    private void setupViewPager(ViewPager viewPager){
        TabSectionsPageAdapter adapter = new TabSectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabActiveFragment(), "AKTIVA");
        adapter.addFragment(new TabAllFragment(), "ALLA");
        adapter.addFragment(new TabTipFragment(), "TIPS");

        viewPager.setAdapter(adapter);


    }
}
