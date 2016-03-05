package com.example.lisa.serietracker;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    //private ArrayAdapter<Serie> serieArrayAdapter;
    private SerieAdapter serieArrayAdapter;
    private DataSource datasource;

    public static final String EXTRA_SERIE_ID = "extraSerieId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (ListView) findViewById(R.id.listView);

        datasource = new DataSource(this);
        List<Serie> series = datasource.getAllSeries();
        //serieArrayAdapter = new ArrayAdapter<Serie>(this,android.R.layout.simple_list_item_1,series);
        serieArrayAdapter = new SerieAdapter(this,R.layout.row_item,series);
        listView.setAdapter(serieArrayAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create an Intent
                Intent intent = new Intent(MainActivity.this, SerieDetailsActivity.class);
                intent.putExtra(EXTRA_SERIE_ID, serieArrayAdapter.getItem(position).getId());
               /* Serie clickedItem = (Serie) parent.getItemAtPosition(position);
                intent.putExtra("title", clickedItem.getTitle());
                intent.putExtra("status", clickedItem.getStatus());
                intent.putExtra("ep", clickedItem.getEp());
                intent.putExtra("rating", clickedItem.getRating());*/

                //Open the new screen by starting the activity
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, NewSerieActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            long serieId = data.getLongExtra(EXTRA_SERIE_ID, -1);
            //Check if the request code is correct
            if (serieId != -1) {
                //Everything's fine, get the values;

                Serie serie = datasource.getSerie(serieId);
                serieArrayAdapter.add(serie);
                updateSerieListView();



                /*Create a list item from the values
                Serie newSerie = new Serie(title, ep,status,rating);

                //Add the new item to the adapter;
                series.add(newSerie);*/

                //Have the adapter update
                serieArrayAdapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void updateSerieListView() {
        List<Serie> series = datasource.getAllSeries();
        //serieArrayAdapter = new ArrayAdapter<Serie>(this, android.R.layout.simple_list_item_1, series);
        serieArrayAdapter = new SerieAdapter(this,R.layout.row_item,series);
        listView.setAdapter(serieArrayAdapter);
    }

}
