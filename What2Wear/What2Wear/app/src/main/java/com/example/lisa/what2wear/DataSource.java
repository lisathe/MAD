package com.example.lisa.what2wear;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lisa on 25-2-2016.
 */
public class DataSource {

    private SQLiteDatabase database; // Add, retrieve, edit or delete things from the database.
    private MySQLiteHelper dbHelper;// get the database
    private String[] assignmentAllColumns = { MySQLiteHelper.COLUMN_CLOTH_ID, MySQLiteHelper.COLUMN_CLOTH }; //For database calls if you want all columns.

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

    public long createCloth(String info) {
        // If the database is not open yet, open it
        if (!database.isOpen())
            open();

        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_CLOTH, info);
        long insertId = database.insert(MySQLiteHelper.TABLE_CLOTHES, null, values);

        // If the database is open, close it
        if (database.isOpen())
            close();

        return insertId;
    }

    public void deleteCloth(Cloth cloth) {
        if (!database.isOpen())
            open();

        database.delete(MySQLiteHelper.TABLE_CLOTHES, MySQLiteHelper.COLUMN_CLOTH_ID + " =?", new String[] { Long.toString(cloth.getId())});

        if (database.isOpen())
            close();

    }


    public void updateCloth(Cloth cloth) {
        if (!database.isOpen())
            open();

        ContentValues args = new ContentValues();
        args.put(MySQLiteHelper.COLUMN_CLOTH, cloth.getInfo());
        database.update(MySQLiteHelper.TABLE_CLOTHES, args, MySQLiteHelper.COLUMN_CLOTH_ID + "=?", new String[]{Long.toString(cloth.getId())});
        if (database.isOpen())
            close();
    }


    public List<Cloth> getAllClothes() {
        if (!database.isOpen())
            open();

        List<Cloth> assignments = new ArrayList<Cloth>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CLOTHES, assignmentAllColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cloth cloth = cursorToCloth(cursor);
            assignments.add(cloth);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();

        if (database.isOpen())
            close();

        return assignments;
    }


    private Cloth cursorToCloth(Cursor cursor) {
        try {
            Cloth cloth = new Cloth();
            cloth.setId(cursor.getLong(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_CLOTH_ID)));
            cloth.setInfo(cursor.getString(cursor.getColumnIndexOrThrow(MySQLiteHelper.COLUMN_CLOTH)));
            return cloth;
        } catch(CursorIndexOutOfBoundsException exception) {
            exception.printStackTrace();
            return null;
        }
    }


    public Cloth getCloth(long columnId) {
        if (!database.isOpen())
            open();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_CLOTHES, assignmentAllColumns, MySQLiteHelper.COLUMN_CLOTH_ID + "=?", new String[] { Long.toString(columnId)}, null, null, null);

        cursor.moveToFirst();
        Cloth cloth = cursorToCloth(cursor);
        cursor.close();

        if (database.isOpen())
            close();

        return cloth;
    }
}

