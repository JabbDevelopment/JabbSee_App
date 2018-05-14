package com.jabb.jabbsee.helpers;

import android.util.Log;

import com.jabb.jabbsee.communicators.LibraryCommunicator;
import com.jabb.jabbsee.models.Serie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SerieListHelper {

    private static SerieListHelper listHelperInstance;
    private List<Serie> serieList;
    private LibraryCommunicator libraryCommunicator;

    private SerieListHelper() {
        libraryCommunicator = new LibraryCommunicator();
        serieList = new ArrayList<>();
    }

    public static SerieListHelper getInstance(){
        if(listHelperInstance == null)
            listHelperInstance = new SerieListHelper();
        return listHelperInstance;
    }

    public List<Serie> getActiveSerieList() {
        return serieList;
    }

    public void updateSerieList() throws IOException {

        serieList = libraryCommunicator.getLibrary().getSeriesList();

        for(Serie s: serieList) {
            Log.d("SerieListItem", s.getTitle());
            Log.d("SerieListItem", String.valueOf(s.getSeason()));
            Log.d("SerieListItem", String.valueOf(s.getEpisode()));
        }
    }
}
