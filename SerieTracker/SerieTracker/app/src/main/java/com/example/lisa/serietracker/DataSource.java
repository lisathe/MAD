package com.example.lisa.serietracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lisa on 4-3-2016.
 */
public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
  //  private String[] serieAllColums = { MySQLiteHelper.COLUMN_SERIES_ID, MySQLiteHelper.COLUMN_SERIE};

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
    }

    public long createSerie (Serie serie)
    {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE,serie.getTitle());
        values.put(MySQLiteHelper.COLUMN_STATUS,serie.getStatus());
        values.put(MySQLiteHelper.COLUMN_EP,serie.getEp());
        values.put(MySQLiteHelper.COLUMN_RATING,serie.getRating());

        long insertId = database.insert(MySQLiteHelper.TABLE_SERIES, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;

    }

    public void deleteSerie(Serie serie) {
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_SERIES, MySQLiteHelper.COLUMN_SERIES_ID + " =?", new String[] { Long.toString(serie.getId())});

        if (database.isOpen())
            close();

    }


    public void updateSerie(Serie serie) {
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE,serie.getTitle());
        values.put(MySQLiteHelper.COLUMN_STATUS,serie.getStatus());
        values.put(MySQLiteHelper.COLUMN_EP,serie.getEp());
        values.put(MySQLiteHelper.COLUMN_RATING,serie.getRating());

        database.update(MySQLiteHelper.TABLE_SERIES, values, MySQLiteHelper.COLUMN_SERIES_ID + "=?", new String[]{Long.toString(serie.getId())});
        if (database.isOpen())
            close();
    }

  /*  public List<Serie> getSeries() {
        if (!database.isOpen())
            open();

        List<Serie> series = new ArrayList<Serie>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERIES,... , null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Serie serie = curserToSerie(cursor);
            assignments.add(assignment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return assignments;
    }*/


}
