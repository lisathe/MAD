package com.example.lisa.serietracker;

import android.widget.Spinner;

/**
 * Created by Lisa on 3-3-2016.
 */
public class Serie {

    private String title, ep, status, rating;
    private long id;

    //Constructor
    /*public Serie(String title, String ep, String status, String rating)
    {
        this.title= title;
        this.ep = ep;
        this.status = status;
        this.rating = rating;
    }*/

    //Getters
    public String getTitle()
    {
        return title;
    }

    public String getEp() {
        return ep;
    }

    public String getStatus() {
        return status;
    }

    public String getRating() {
        return rating;
    }

    public long getId()
    {
        return id;
    }

    //Setters

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEp(String ep) {
        this.ep = ep;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}

