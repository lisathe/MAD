package com.example.lisa.what2wear;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    MultiSelectionSpinner spinnerColor;
    MultiSelectionSpinner spinnerBottom;
    MultiSelectionSpinner spinnerThemes;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerColor=(MultiSelectionSpinner)findViewById(R.id.color);
        spinnerBottom=(MultiSelectionSpinner)findViewById(R.id.bottom);
        spinnerThemes=(MultiSelectionSpinner)findViewById(R.id.themes);

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
