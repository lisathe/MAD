package com.example.lisa.shoppinglistadvanced;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    //Views
    private ListView listView;
    private ItemAdapter adapter;
    private List<ListItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Init views
        listView = (ListView) findViewById(R.id.listView);
        //Create list of items
        items = new ArrayList<ListItem>();

        registerForContextMenu(listView);
        adapter = new ItemAdapter(this,R.layout.row_item,items);

        items.add(new ListItem("Apple", "Granny Smith", R.mipmap.ic_launcher));
        items.add(new ListItem("Banana", "Chiquita banana", R.mipmap.ic_launcher));
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Create an Intent
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);

                ListItem clickedItem = (ListItem) parent.getItemAtPosition(position);
                intent.putExtra("title", clickedItem.getTitle());
                intent.putExtra("description", clickedItem.getDescription());
                intent.putExtra("image-resource", clickedItem.getImageResource());
                //Open the new screen by starting the activity
                startActivity(intent);

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), NewItemActivity.class);
                startActivityForResult(intent, 1234);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {

        //Get the clicked item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        //Inflate the context menu from the resource file
        getMenuInflater().inflate(R.menu.context_menu, menu);

        //Find the delete MenuItem by its ID
        MenuItem deleteButton = menu.findItem(R.id.context_menu_delete_item);
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Retrieve info about the long pressed list item
        AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getItemId() == R.id.context_menu_delete_item) {
            //Remove the item from the list
            items.remove(itemInfo.position);
            //Update the adapter to reflect the list change
            adapter.notifyDataSetChanged();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
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
        if (item.getItemId() == R.id.action_bar_menu_delete_all) {
        //Clears the list
            items.clear();

        //Tell the adapter that it should reload the data
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            //Check if the request code is correct
            if (requestCode == 1234) {
                //Everything's fine, get the values;
                String title = data.getStringExtra("title");
                String description = data.getStringExtra("description");
                int imageResource = data.getIntExtra("image-resource", 0);

                //Create a list item from the values
                ListItem item = new ListItem(title, description, imageResource);

                //Add the new item to the adapter;
                items.add(item);

                //Have the adapter update
                adapter.notifyDataSetChanged();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
