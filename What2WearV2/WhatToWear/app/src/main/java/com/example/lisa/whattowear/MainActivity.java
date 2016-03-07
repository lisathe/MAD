package com.example.lisa.whattowear;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button addToWardrobe;
    Button favOutfit;
    Button viewWardrobe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addToWardrobe = (Button)findViewById(R.id.btn_wardrobe);
        favOutfit = (Button)findViewById(R.id.btn_fav_outfit);
        viewWardrobe = (Button) findViewById(R.id.btn_view_wardrobe);

        // Button intents
        addToWardrobe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(MainActivity.this, AddToWardrobe.class);
                startActivity(view);
            }
        });
    }

}
