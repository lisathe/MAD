package com.example.lisa.what2wear;

import android.graphics.Bitmap;

/**
 * Created by Lisa on 25-2-2016.
 */
public class Cloth
{
    private long id;
    private String info;
    private Bitmap picture;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getInfo()
    {

        return info;
    }

    public void setInfo(String info)
    {
        this.info = info;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return info;
    }

    public Bitmap getPicture()
    {
        return picture;
    }

}
