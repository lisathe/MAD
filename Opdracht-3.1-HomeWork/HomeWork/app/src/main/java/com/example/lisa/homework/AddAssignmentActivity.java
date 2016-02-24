package com.example.lisa.homework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class AddAssignmentActivity extends AppCompatActivity {

    private EditText assignmentText;
    private DataSource datasource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        assignmentText = (EditText) findViewById(R.id.add_assignment_edit_text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long assignmentId = datasource.createAssignment(assignmentText.getText().toString());
                Intent resultIntent = new Intent();
                resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

}
