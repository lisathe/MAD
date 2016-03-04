package com.example.lisa.serietracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Lisa on 4-3-2016.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    // Database info
    private static final String DATABASE_NAME = "myseriesdatabase.db";
    private static final int DATABASE_VERSION = 1;

    //Series
    public static final String TABLE_SERIES = "series";
    public static final String COLUMN_SERIES_ID = "serie_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_EP = "ep";
    public static final String COLUMN_RATING = "rating";

    //Create table
    private static final String DATABASE_CREATE_SERIES =
            "CREATE TABLE" + TABLE_SERIES +
                    "(" +
                    COLUMN_SERIES_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " text not null" +
                    COLUMN_STATUS+ " text not null" +
                    COLUMN_EP + " text" +
                    COLUMN_RATING + " text" +
                    ");";


    // Mandatory constructor which passes the context, database name and database version and passes it to the parent
    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        // Execute the sql to create the table assignments
        database.execSQL(DATABASE_CREATE_SERIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	/*
     	* When the database gets upgraded you should handle the update to make sure there is no data loss.
     	* This is the default code you put in the upgrade method, to delete the table and call the oncreate again.
     	*/
        Log.w(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERIES);
        onCreate(db);
    }


}
