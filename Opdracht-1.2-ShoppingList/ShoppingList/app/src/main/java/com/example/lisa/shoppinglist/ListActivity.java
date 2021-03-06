package com.example.lisa.shoppinglist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    // Adapter and ArrayList
    private ArrayAdapter<String> adapter;
    private List<String> items;
    private EditText addItemEditText;


    //Views
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Init the views
        listView = (ListView) findViewById(R.id.listView);
        addItemEditText = (EditText) findViewById(R.id.editText);

        //Create the List of items
        items = new ArrayList<String>();

        //Create the Array Adapter, give it a layout and a list of values
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        //Add some items to the Array list
        items.add("Strawberry");
        items.add("Mango");
        adapter.notifyDataSetChanged();

        //Set the newly created adapter as the adapter for the listview
        listView.setAdapter(adapter);

        //Set the listview on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View listItem, int position, long id) {
                //Get the value of the item that the user clicked on
                String clickedItem = (String) parent.getItemAtPosition(position);

                //Display a  message to show the user the item he/she clicked on
                Snackbar.make(parent, "Clicked: " + clickedItem, Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        registerForContextMenu(listView);

    }
    public void addListItem(View view) {
        //Get the user text from the textfield
        String text = addItemEditText.getText().toString();

        //Check if some text has been added
        if (!(TextUtils.isEmpty(text))) {
            //Add the text to the adapter
            items.add(text);

            //Notify the adapter that the action_bar_menu of data has changed and the view should be updated
            adapter.notifyDataSetChanged();

            //Clear the EditText for the next item
            addItemEditText.setText("");
        } else {
            //Show a message to the user if the textfield is empty
            Snackbar.make(view, "Please enter some text in the textfield", Snackbar.LENGTH_LONG).setAction("Action", null).show();

        }
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        //Get the clicked item
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        //Get the name of the clicked item
        String clickedItem = (String) listView.getItemAtPosition(info.position);

        //Inflate the context menu from the resource file
        getMenuInflater().inflate(R.menu.context_menu, menu);

        //Find the delete MenuItem by its ID
        MenuItem deleteButton = menu.findItem(R.id.context_menu_delete_item);

        //Get the title from the menu button
        String originalTitle = deleteButton.getTitle().toString();

        //Make a new title combining the original title and the name of the clicked list item
        deleteButton.setTitle(originalTitle + " '" + clickedItem + "'?");

        //Let Android do its magic
        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
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
}
