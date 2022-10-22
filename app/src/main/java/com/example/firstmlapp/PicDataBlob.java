package com.example.firstmlapp;

import android.graphics.drawable.Drawable;

public class PicDataBlob {

    private Drawable image;
    private long timetaken;
    private String[] tags;
    private double timetotake;

    public PicDataBlob(Drawable image, long timetaken, String[] tags, double timetotake) {
        this.image = image;
        this.timetaken = timetaken;
        this.tags = tags;
        this.timetotake = timetotake;
    }

    public long getTimetaken() {
        return timetaken;
    }

    public String[] getTags() {
        return tags;
    }

    public double getTimetotake() {
        return timetotake;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public void setTimetaken(long timetaken) {
        this.timetaken = timetaken;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public void setTimetotake(double timetotake) {
        this.timetotake = timetotake;
    }
}
