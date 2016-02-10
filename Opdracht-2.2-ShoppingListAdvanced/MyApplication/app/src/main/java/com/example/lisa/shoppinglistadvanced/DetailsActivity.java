package com.example.lisa.shoppinglistadvanced;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    private TextView title,description;
    private ImageView icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        title = (TextView) findViewById(R.id.activity_details_title);
        description = (TextView) findViewById(R.id.activity_details_description);
        icon = (ImageView) findViewById(R.id.activity_details_icon);

        //Get the values from the intent
        int imageResource = getIntent().getIntExtra("image-resource", 0);
        String titleString = getIntent().getStringExtra("title");
        String descriptionString = getIntent().getStringExtra("description");

        //Set the values from the intent to the views
        icon.setImageResource(imageResource);
        title.setText(titleString);
        description.setText(descriptionString);

    }
}
