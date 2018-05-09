package com.jabb.jabbsee.helpers;

import com.jabb.jabbsee.communicators.LibraryCommunicator;
import com.jabb.jabbsee.models.Serie;

import java.util.List;

public class SerieListHelper {

    private static SerieListHelper listHelperInstance;
    private List<Serie> serieList;
    private LibraryCommunicator libraryCommunicator;

    private SerieListHelper() {
        libraryCommunicator = new LibraryCommunicator();

        updateSerieList();
    }

    public static SerieListHelper getInstance(){
        if(listHelperInstance == null)
            listHelperInstance = new SerieListHelper();
        return listHelperInstance;
    }

    public List<Serie> getActiveSerieList() {
        updateSerieList();
        return serieList;
    }

    public void updateSerieList(){
        serieList = libraryCommunicator.getLibrary().getSeriesList();

        /*
        serieList = new ArrayList<>();
        Serie serie1 = new Serie("Arrow");
        Serie serie2 = new Serie("Once upon a time");
        Serie serie3 = new Serie("Game of thrones");
        Serie serie4 = new Serie("Advokaten");
        Serie serie5 = new Serie("Billions");
        Serie serie6 = new Serie("Jordskott");
        Serie serie7 = new Serie("Kungen");
        Serie serie8 = new Serie("Svanarna");
        serieList.add(serie1);
        serieList.add(serie2);
        serieList.add(serie3);
        serieList.add(serie4);
        serieList.add(serie5);
        serieList.add(serie6);
        serieList.add(serie7);
        serieList.add(serie8);
        */

    }
}
