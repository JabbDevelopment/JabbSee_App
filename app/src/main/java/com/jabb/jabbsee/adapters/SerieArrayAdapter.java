package com.jabb.jabbsee.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jabb.jabbsee.Constants;
import com.jabb.jabbsee.R;
import com.jabb.jabbsee.models.Serie;

import java.util.List;

public class SerieArrayAdapter extends ArrayAdapter<Serie> {

    private LayoutInflater mInflater;
    private List<Serie> serieList;
    private final String TAG = Constants.LOGGING_TAG_PREFIX + SerieArrayAdapter.class.getSimpleName();

    public SerieArrayAdapter(Context context, int resource, List<Serie> serieList){
        super(context, resource, serieList);

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        this.serieList = serieList;

        Log.d(TAG, "I SerieArrayAdapter");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.listview_detail, parent, false);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView seasonTextView = view.findViewById(R.id.seasonTextView);
        TextView episodeTextView = view.findViewById(R.id.episodeTextView);

        Serie serie = serieList.get(position);

        titleTextView.setText(serie.getTitle());
        //seasonTextView.setText(serie.getSeason());
        //episodeTextView.setText(serie.getEpisode());
        Log.d(TAG, "i getView " + serie.getTitle());

        return view;
    }
}
