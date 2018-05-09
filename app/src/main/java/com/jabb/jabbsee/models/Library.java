package com.jabb.jabbsee.models;

import java.io.Serializable;
import java.util.List;

public class Library implements Serializable {

    private List<Serie> seriesList;
    private String owner;

    public Library() {

    }

    public List<Serie> getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(List<Serie> seriesList) {
        this.seriesList = seriesList;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
