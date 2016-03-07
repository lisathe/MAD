package com.example.lisa.serietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SerieDetailsActivity extends AppCompatActivity {

    private TextView title, status, episode, rating;
    private DataSource datasource;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_details);

        datasource = new DataSource(this);
        long serieId = getIntent().getLongExtra(MainActivity.EXTRA_SERIE_ID, -1);
        serie = datasource.getSerie(serieId);

        title = (TextView) findViewById(R.id.activity_details_title);
        title.setText(serie.getTitle().toString());

        status = (TextView) findViewById(R.id.activity_details_status);
        status.setText(serie.getStatus().toString());

        episode = (TextView) findViewById(R.id.activity_details_ep);
        episode.setText(serie.getEp().toString());

        rating = (TextView) findViewById(R.id.activity_details_rating);
        rating.setText(serie.getRating().toString());



    }
}
