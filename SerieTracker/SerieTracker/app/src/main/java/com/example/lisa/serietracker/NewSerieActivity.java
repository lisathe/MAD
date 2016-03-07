package com.example.lisa.serietracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class NewSerieActivity extends AppCompatActivity {


    private Spinner ratingSpinner, statusSpinner;
    private Button addBtn;
    private EditText title, ep;
    private DataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_serie);

        ratingSpinner = (Spinner) findViewById(R.id.rating_spinner);
        statusSpinner = (Spinner) findViewById(R.id.status_spinner);
        addBtn = (Button) findViewById(R.id.btn_add_serie);
        title = (EditText)findViewById(R.id.edit_title);
        ep = (EditText)findViewById(R.id.edit_ep);
        dataSource = new DataSource(this);


        // Create spinner items
        String[] spinnerRatings = {"Please select rating","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        final String[] spinnerStatus = {"Please select a status", "Plan to watch", "Watching", "Complete", "On hold", "Dropped"};

        // Create spinner adapter
        ArrayAdapter<String> rateSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerRatings);
        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerStatus);

        ratingSpinner.setAdapter(rateSpinnerAdapter);
        statusSpinner.setAdapter(statusSpinnerAdapter);

        //Handle the button click
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the title and status spinner have a value
                if (!TextUtils.isEmpty(title.getText()) && statusSpinner.getSelectedItemPosition() > 0) {

                    long serieId = dataSource.createSerie(title.getText().toString(),statusSpinner.getSelectedItem().toString(),ep.getText().toString(),ratingSpinner.getSelectedItem().toString());
                    //Create a new intent with the data
                    Intent result = new Intent();
                    result.putExtra(MainActivity.EXTRA_SERIE_ID, serieId);
                   /* data.putExtra("title", title.getText().toString());
                    data.putExtra("status", statusSpinner.getSelectedItem().toString());
                    data.putExtra("ep", ep.getText().toString());
                    data.putExtra("rating", ratingSpinner.getSelectedItem().toString());*/

                    //Send the result back to the activity
                    setResult(Activity.RESULT_OK, result);
                    //Finish this activity
                    finish();
                }
                else{
                    //Give warning to user
                    Toast.makeText(NewSerieActivity.this, "Please enter a title and select a status", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
