package com.jabb.jabbsee.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jabb.jabbsee.R;

public class SerieAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private String[] series;
    private String[] seasons;
    private String[] episodes;


    public SerieAdapter(Context context, String[] series, String[] seasons, String[] episodes){

        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.series = series;
        this.seasons = seasons;
        this.episodes = episodes;

    }

    @Override
    public int getCount() {
        return series.length;
    }

    @Override
    public Object getItem(int position) {
        return series[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = mInflater.inflate(R.layout.listview_detail, parent, false);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        TextView seasonTextView = view.findViewById(R.id.seasonTextView);
        TextView episodeTextView = view.findViewById(R.id.episodeTextView);

        String serie = series[position];
        String season = seasons[position];
        String episode = episodes[position];

        titleTextView.setText(serie);
        seasonTextView.setText(season);
        episodeTextView.setText(episode);

        return view;
    }
}
