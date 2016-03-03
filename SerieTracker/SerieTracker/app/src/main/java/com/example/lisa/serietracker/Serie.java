package com.example.lisa.serietracker;

import android.widget.Spinner;

/**
 * Created by Lisa on 3-3-2016.
 */
public class Serie {

    private String title, ep, status, rating, notes;

    //Constructor
    public Serie(String title, String ep, String status, String rating, String notes)
    {
        this.title= title;
        this.ep = ep;
        this.status = status;
        this.rating = rating;
        this.notes = notes;
    }

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

    public String getNotes() {
        return notes;
    }
}

