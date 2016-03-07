package com.example.lisa.serietracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lisa on 4-3-2016.
 */
public class DataSource {

    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] seriesAllColumns = {MySQLiteHelper.COLUMN_SERIES_ID, MySQLiteHelper.COLUMN_TITLE, MySQLiteHelper.COLUMN_STATUS, MySQLiteHelper.COLUMN_EP, MySQLiteHelper.COLUMN_RATING};
    public static final String LOGTAG = "EXPLORECA";

    public DataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
        database = dbHelper.getWritableDatabase();
        dbHelper.close();
    }

    // Opens the database to use it
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
        Log.i(LOGTAG, "Database opened");
    }

    // Closes the database when you no longer need it
    public void close() {
        dbHelper.close();
        Log.i(LOGTAG, "Database closed");
    }

    public long createSerie (String title, String status, String ep, String rating)
    {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_TITLE,title);
        values.put(MySQLiteHelper.COLUMN_STATUS,status);
        values.put(MySQLiteHelper.COLUMN_EP,ep);
        values.put(MySQLiteHelper.COLUMN_RATING,rating);

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

   public List<Serie> getAllSeries() {
        if (!database.isOpen())
            open();

        List<Serie> series = new ArrayList<Serie>();

       //Selects all the columns from the database table
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERIES, seriesAllColumns, null, null, null, null, null);


       cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Serie serie = cursorToSerie(cursor);
            series.add(serie);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return series;
    }

    private Serie cursorToSerie(Cursor cursor) {
        try {
            Serie serie = new Serie();
            serie.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_SERIES_ID)));
            serie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_TITLE)));
            serie.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_STATUS)));
            serie.setEp(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_EP)));
            serie.setRating(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_RATING)));
            return serie;
        } catch(CursorIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public Serie getSerie(long columnId) {
        if (!database.isOpen())
            open();


        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERIES, seriesAllColumns, MySQLiteHelper.COLUMN_SERIES_ID + "=?", new String[] { Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Serie serie = cursorToSerie(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return serie;
    }


}
