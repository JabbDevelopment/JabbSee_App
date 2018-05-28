package com.jabb.jabbsee.helpers;

import android.util.Log;

import com.jabb.jabbsee.communicators.LibraryCommunicator;
import com.jabb.jabbsee.models.Library;
import com.jabb.jabbsee.models.Serie;

import java.io.IOException;

public class SerieListHelper {

    private static SerieListHelper listHelperInstance;
    //private List<Serie> serieList;
    private Library activeLibrary;

    private LibraryCommunicator libraryCommunicator;

    private SerieListHelper() {
        libraryCommunicator = new LibraryCommunicator();
        //serieList = new ArrayList<>();
    }

    public static SerieListHelper getInstance(){
        if(listHelperInstance == null)
            listHelperInstance = new SerieListHelper();
        return listHelperInstance;
    }

    public Library getActiveLibrary() {
        return activeLibrary;
    }

    public void updateLibraryFromServer() throws IOException {

        activeLibrary = libraryCommunicator.getLibrary();
        //serieList = activeLibrary.getSeriesList();
        for(Serie s: activeLibrary.getSeriesList()) {
            Log.d("SerieListItem", s.getTitle());
            Log.d("SerieListItem", String.valueOf(s.getSeason()));
            Log.d("SerieListItem", String.valueOf(s.getEpisode()));
        }
    }

    public void updateSerie(Serie serie, int serieIndex) throws IOException {
        activeLibrary.getSeriesList().set(serieIndex, serie);
        libraryCommunicator.updateLibrary(activeLibrary);
    }
}
