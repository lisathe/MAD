package com.example.lisa.what2wear;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddToWardrobeActivity extends AppCompatActivity {

    MultiSelectionSpinner spinnerColor;
    MultiSelectionSpinner spinnerBottom;
    MultiSelectionSpinner spinnerThemes;

    private Button button;
    private EditText editTitleText, editDescriptionText;
    private Spinner iconSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_wardrobe);

        spinnerColor=(MultiSelectionSpinner)findViewById(R.id.color);
        spinnerBottom=(MultiSelectionSpinner)findViewById(R.id.bottom);
        spinnerThemes=(MultiSelectionSpinner)findViewById(R.id.themes);
        button = (Button) findViewById(R.id.button);
        editTitleText = (EditText)findViewById(R.id.editTitleText);
        editDescriptionText = (EditText) findViewById(R.id.editDescriptionText);
        iconSpinner = (Spinner) findViewById(R.id.spinner);

        //Create the spinner items
        String[] spinnerItems = {"T-Shirt", "Jeans", "Skirt", "Coat","Dress","Sweaters"};

        //Create the spinner adapter
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, spinnerItems);

        //Set the adapter for the spinner
        iconSpinner.setAdapter(spinnerAdapter);


        //Color list
        List<String> colors = new ArrayList<String>();
        colors.add("Black");
        colors.add("White");
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red");
        colors.add("Yellow");
        colors.add("Purple");
        colors.add("Pink");
        colors.add("Grey");
        spinnerColor.setItems(colors);

        //Bottom list
        List<String> bottom = new ArrayList<String>();
        bottom.add("Pants");
        bottom.add("Skirts");
        bottom.add("Leggings");
        bottom.add("Shorts");
        spinnerBottom.setItems(bottom);


        //Themes list
        List<String> themes = new ArrayList<String>();
        themes.add("Cute");
        themes.add("Casual");
        themes.add("Formal");
        themes.add("Party");
        themes.add("Vintage");
        themes.add("Work");
        spinnerThemes.setItems(themes);
    }
}
