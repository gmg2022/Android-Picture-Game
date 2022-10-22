package com.example.firstmlapp;

import com.example.firstmlapp.PicDataBlob;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

public class FourthActivity extends AppCompatActivity {
    private ListView listView;

    public static PicDataBlob[][] saveddata = SecondActivity.picdata;
    public static ArrayList<PicDataBlob> savedatalist = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        //Sets up the board and music, grows a random box
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recentphotos);
        ArrayList<Character> letterlist = setList();

        String[] letters = new String[savedatalist.size()];
        Drawable[] images = new Drawable[savedatalist.size()];
        String[] tags = new String[savedatalist.size()];
        String[] timetotake = new String[savedatalist.size()];
        String[] timetaken = new String[savedatalist.size()];

        for (int i = 0; i < images.length; i++) {
            //saving the tags in a String (tag1, tag2, tag3)
            String temp = "";
            String[] temptags = savedatalist.get(i).getTags();
            temp += temptags[0] + ", " + temptags[1] + ", " + temptags[2];
            tags[i] = temp;

            //saving the image
            images[i] = savedatalist.get(i).getImage();

            //adding the data in arraylist
            if (i >= letterlist.size()) {

            } else {
                letters[i] = "" + letterlist.get(i);
            }
            //formatting time taken as a String
            timetotake[i] = "" + savedatalist.get(i).getTimetotake() + " seconds";
            Date tempdate = new Date(savedatalist.get(i).getTimetaken());
            timetaken[i] = "" + tempdate;
        }

        // Setting header
        TextView textView = new TextView(this);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setText("Recent Pictures");

        ListView listView=findViewById(R.id.list);
        listView.addHeaderView(textView);

        // For populating list data
        ImageDataObject customCountryList = new ImageDataObject(this, letters, tags, images, timetotake);
        listView.setAdapter(customCountryList);

        System.out.println(savedatalist.size());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(),"It took " + timetotake[position-1]+ " to take \nPicture taken at " + timetaken[position - 1],Toast.LENGTH_SHORT).show();        }
        });
    }

    public static ArrayList<Character> setList() {
        ArrayList<Character> letterlist = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < 3; j++) {
                if (saveddata[i][j] != null) {
                    savedatalist.add(saveddata[i][j]);
                    letterlist.add((char)(i+65));
                }
            }
        }
        return letterlist;
    }
}
