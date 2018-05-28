package com.jabb.jabbsee.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.activities.SerieSelectedActivity;
import com.jabb.jabbsee.adapters.SerieArrayAdapter;
import com.jabb.jabbsee.helpers.SerieListHelper;
import com.jabb.jabbsee.models.Serie;

import java.util.List;


public class TabContentFragment extends Fragment {

    private final String TAG = Constants.LOGGING_TAG_PREFIX + TabContentFragment.class.getSimpleName();
    private ListView serieListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate TabContentFragment");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tab_content, container, false);

        serieListView = view.findViewById(R.id.serieListView);


        final List<Serie> serieList =  SerieListHelper.getInstance().getActiveLibrary().getSeriesList();
        Log.d(TAG, "!!!!!!!!!!!!!!!!SerieList in tabContent: " + serieList.toString());
        for(Serie serie: serieList){
            Log.d(TAG, "Serie title: " + serie.getTitle());
        }

        SerieArrayAdapter serieArrayAdapter = new SerieArrayAdapter(getActivity(), R.layout.listview_detail, serieList);
        serieListView.setAdapter(serieArrayAdapter);

        serieListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Intent showSerieSelectedActivity = new Intent(getActivity(), SerieSelectedActivity.class);
                //showSerieSelectedActivity.putExtra("com.jabb.SERIE_INDEX", serieList.get(position).getTitle());
                showSerieSelectedActivity.putExtra(Constants.SERIE_INDEX_KEY, position);
                showSerieSelectedActivity.putExtra(Constants.SERIE_KEY, serieList.get(position));
                startActivity(showSerieSelectedActivity);
            }
        });

        return view;
    }


}
