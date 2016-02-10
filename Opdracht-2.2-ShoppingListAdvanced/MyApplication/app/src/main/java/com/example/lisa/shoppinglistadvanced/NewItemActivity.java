package com.example.lisa.shoppinglistadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewItemActivity extends AppCompatActivity {

    private Button button;
    private EditText titleText, descriptionText;
    private Spinner iconSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        button = (Button) findViewById(R.id.button);
        titleText = (EditText)findViewById(R.id.titleText);
        descriptionText = (EditText) findViewById(R.id.descriptionText);
        iconSpinner = (Spinner) findViewById(R.id.spinner);
        //Create the spinner items
        String[] spinnerItems = {"Apple", "Banana", "Pear"};

        //Create the spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerItems);

        //Set the adapter for the spinner
        iconSpinner.setAdapter(spinnerAdapter);
    }
}
