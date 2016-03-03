package com.example.lisa.serietracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class NewSerieActivity extends AppCompatActivity {


    private Spinner ratingSpinner, statusSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_serie);

         ratingSpinner = (Spinner) findViewById(R.id.rating_spinner);
         statusSpinner = (Spinner)findViewById(R.id.status_spinner);

        // Create spinner items
        String[] spinnerRatings = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String[] spinnerStatus = {"Plan to watch", "Watching", "Complete", "On hold", "Dropped"};

        // Create spinner adapter
        ArrayAdapter<String> rateSpinnerAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,spinnerRatings);
        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<String> (this, android.R.layout.simple_list_item_1,spinnerStatus);

        ratingSpinner.setAdapter(rateSpinnerAdapter);
        statusSpinner.setAdapter(statusSpinnerAdapter);


    }
}
