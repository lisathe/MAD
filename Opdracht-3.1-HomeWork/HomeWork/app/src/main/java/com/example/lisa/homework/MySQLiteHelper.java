package com.example.lisa.homework;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lisa on 24-2-2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    // Database info
    private static final String DATABASE_NAME = "myhomeworktutorial.db"; //The name as it will be saved on your phone
    private static final int DATABASE_VERSION = 1; //The version of the database (should be increased when changing the database for a new version of your app)

    // Assignments
    public static final String TABLE_ASSIGNMENTS = "assignments"; //The name of the table assignments where we’re going to put our assignments in.
    public static final String COLUMN_ASSIGNMENT_ID = "assignment_id";// The id of an assignment to easily retrieve an assignment.
    public static final String COLUMN_ASSIGNMENT = "assignment";//The string of the assignment (we’ll get this from AddAssignmentActivity).

    // Creating the table
    private static final String DATABASE_CREATE_ASSIGNMENTS =
            "CREATE TABLE " + TABLE_ASSIGNMENTS +
                    "(" +
                    COLUMN_ASSIGNMENT_ID + " integer primary key autoincrement, " +
                    COLUMN_ASSIGNMENT + " text not null" +
                    ");";

    // Mandatory constructor which passes the context, database name and database version and passes it to the parent
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Execute the sql to create the table assignments
        database.execSQL(DATABASE_CREATE_ASSIGNMENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	/*
     	* When the database gets upgraded you should handle the update to make sure there is no data loss.
     	* This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
     	*/
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSIGNMENTS);
        onCreate(db);
    }


}
