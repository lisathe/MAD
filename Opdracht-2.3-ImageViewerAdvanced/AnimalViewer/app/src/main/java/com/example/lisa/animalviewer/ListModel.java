package com.example.lisa.animalviewer;

/**
 * Created by Lisa on 15-2-2016.
 */
public class ListModel {

    public ListModel(int imageId, String imageText) {
        this.imageId = imageId;
        this.imageText = imageText;
    }

    int imageId;
    String imageText;

    public int getImageId() {
        return imageId;
    }

    public String getImageText() {
        return imageText;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setImageText(String imageText) {
        this.imageText = imageText;
    }
}
