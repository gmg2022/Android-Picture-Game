package com.example.firstmlapp;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDataObject extends ArrayAdapter {
    private String[] letter;
    private String[] tags;
    private Drawable[] imageid;
    private String[] timeToTake;
    private Activity context;

    public ImageDataObject(Activity context, String[] letter, String[] tags, Drawable[] imageid, String[] timeToTake) {
        super(context, R.layout.row_file, letter);
        this.context = context;
        this.letter = letter;
        this.tags = tags;
        this.imageid = imageid;
        this.timeToTake = timeToTake;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater = context.getLayoutInflater();
        if(convertView==null)
            row = inflater.inflate(R.layout.row_file, null, true);
        TextView textViewCountry = (TextView) row.findViewById(R.id.textViewCountry);
        TextView textViewCapital = (TextView) row.findViewById(R.id.textViewCapital);
        ImageView imageFlag = (ImageView) row.findViewById(R.id.imageViewFlag);

        TextView text2 = (TextView) row.findViewById(R.id.another);
        TextView text3 = (TextView) row.findViewById(R.id.anothertest);

        textViewCountry.setText(letter[position]);
        textViewCapital.setText(tags[position]);
        imageFlag.setImageDrawable(imageid[position]);
        return row;
    }
}
