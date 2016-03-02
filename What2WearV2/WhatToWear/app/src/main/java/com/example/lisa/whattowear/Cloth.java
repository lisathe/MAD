package com.example.lisa.whattowear;

import android.app.Activity;
import android.graphics.Bitmap;
import android.widget.ImageView;

import java.net.URI;

/**
 * Created by Lisa on 1-3-2016.
 */
public class Cloth {

    private String title;
    private String description;
    private URI pictureUri;
    private Activity convertView;
    ImageView selectedPicture = (ImageView) convertView.findViewById(R.id.imageView);

    Bitmap icon;

    //Constructor

    public Cloth (String title, String description, Bitmap icon)
    {
        this.title = title;
        this.description = description;
        this.icon = icon;
    }

    //Getters

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    public Bitmap getIcon()
    {
        return icon;
    }
}
