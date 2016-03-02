package com.example.lisa.whattowear;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
        Bitmap bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        String titleString = getIntent().getStringExtra("title");
        String descriptionString = getIntent().getStringExtra("description");

        //Set the values from the intent to the views
        icon.setImageBitmap(bitmap);
        title.setText(titleString);
        description.setText(descriptionString);

    }
}
