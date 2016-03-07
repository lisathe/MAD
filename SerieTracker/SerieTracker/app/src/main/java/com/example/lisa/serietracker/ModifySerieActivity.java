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
import android.widget.Toast;

public class ModifySerieActivity extends AppCompatActivity {

    private Spinner ratingSpinner, statusSpinner;
    private Button btn;
    private EditText title, ep;
    private DataSource dataSource;
    private Serie serie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_serie);

        ratingSpinner = (Spinner) findViewById(R.id.modify_rating_spinner);
        statusSpinner = (Spinner) findViewById(R.id.modify_status_spinner);
        btn = (Button) findViewById(R.id.btn_modify_serie);
        title = (EditText)findViewById(R.id.modify_title);
        ep = (EditText)findViewById(R.id.modify_ep);
        dataSource = new DataSource(this);

        long serieId = getIntent().getLongExtra(MainActivity.EXTRA_SERIE_ID, -1);
        serie = dataSource.getSerie(serieId);

        // Create spinner items
        String[] spinnerRatings = {"Please select rating","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        final String[] spinnerStatus = {"Please select a status", "Plan to watch", "Watching", "Complete", "On hold", "Dropped"};

        // Create spinner adapter
        ArrayAdapter<String> rateSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerRatings);
        ArrayAdapter<String> statusSpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerStatus);

        ratingSpinner.setAdapter(rateSpinnerAdapter);
        statusSpinner.setAdapter(statusSpinnerAdapter);

        //Handle the button click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if the title and status spinner have a value
                if (!TextUtils.isEmpty(title.getText()) && statusSpinner.getSelectedItemPosition() > 0) {

                    String newTitle = title.getText().toString();
                    String newStatus =statusSpinner.getSelectedItem().toString();
                    String newEp =ep.getText().toString();
                    String newRating =ratingSpinner.getSelectedItem().toString();

                    serie.setTitle(newTitle);
                    serie.setStatus(newStatus);
                    serie.setEp(newEp);
                    serie.setRating(newRating);

                    dataSource.updateSerie(serie);
                    Toast.makeText(ModifySerieActivity.this,"Serie has been updated", Toast.LENGTH_LONG).show();

                    Intent result = new Intent(ModifySerieActivity.this, MainActivity.class);
                    //Send the result back to the activity
                    setResult(Activity.RESULT_OK, result);
                    //Finish this activity
                    finish();
                } else {
                    //Give warning to user
                    Toast.makeText(ModifySerieActivity.this, "Please enter a title and select a status", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
