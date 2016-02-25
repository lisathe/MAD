package com.example.lisa.what2wear;

import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

public class ViewWardrobeActivity extends AppCompatActivity {

    private ListView listView;
    private List<Cloth> clothes;
    private ItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wardrobe);

        listView = (ListView) findViewById(R.id.wardrobeList);
    }
}
