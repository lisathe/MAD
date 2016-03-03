package com.example.lisa.serietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SerieDetailsActivity extends AppCompatActivity {

    private TextView title, status, episode, rating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_details);

        title = (TextView) findViewById(R.id.activity_details_title);
        status = (TextView) findViewById(R.id.activity_details_status);
        episode = (TextView) findViewById(R.id.activity_details_ep);
        rating = (TextView) findViewById(R.id.activity_details_rating);

        //get values from intent
        String titleString = getIntent().getStringExtra("title");
        String statusString = getIntent().getStringExtra("status");
        String epString = getIntent().getStringExtra("ep");
        String ratingString = getIntent().getStringExtra("rating");

        //set values
        title.setText(titleString);
        status.setText(statusString);
        episode.setText(epString);
        rating.setText(ratingString);

    }
}
