package com.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "notesDb";
    public static final String TABLE_NOTES = "notes";


    public static final String KEY_ID = "_id";
    public static final String KEY_LNotes = "label_notes";
    public static final String KEY_TNotes = "text_notes";
    public static final String KEY_DATE = "create_date";

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("create table " + TABLE_NOTES + "(" +
                KEY_ID + " integer primary key," +
                KEY_LNotes + " text," +
                KEY_TNotes + " text," +
                KEY_DATE + " text" + ");");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + TABLE_NOTES);

        onCreate(db);
    }

}
