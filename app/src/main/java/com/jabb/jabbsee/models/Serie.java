package com.jabb.jabbsee.models;

import java.io.Serializable;
import java.util.Date;

public class Serie implements Serializable {

    private String title;
    private int season;
    private int episode;
    private int ratings;
    private boolean isActive;
    private String comment;
    private Date created;
    private Date changed;

    public Serie(String title, int season, int episode, int ratings, boolean isActive, String comment) {
        this.title = title;
        this.season = season;
        this.episode = episode;
        this.ratings = ratings;
        this.isActive = isActive;
        this.comment = comment;
    }

    public Serie(){}

    public Serie(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getChanged() {
        return changed;
    }

    public void setChanged(Date changed) {
        this.changed = changed;
    }
}
