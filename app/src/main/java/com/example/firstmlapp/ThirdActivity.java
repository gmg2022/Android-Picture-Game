package com.example.firstmlapp;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    // variable for bar chart
    BarChart barChart;

    // variable for bar data.
    BarData barData;

    // variable for bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    protected void onCreate(Bundle savedInstanceState) {
        //Sets up the board and music, grows a random box
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bargraph);

        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Time to Take");

        // creating a new bar data and
        // passing bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to bar chart.
        barChart.setData(barData);

        // adding color to bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            barEntriesArrayList.add(new BarEntry(i+1, (float) SecondActivity.timeToTake[i]));
        }

    }
}
