package com.example.mynotes.Helpers;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mynotes.Model.NoteModel;
import com.example.mynotes.View.AddNoteView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class DBHelper extends SQLiteOpenHelper {

    AddNoteView notes;

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

    public void addNote(Context context, String lNote, String tNote , SQLiteDatabase database)
    {
        final Date date = new Date();
        final SimpleDateFormat datenow = new SimpleDateFormat("dd.MM.yyyy");
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_LNotes, lNote.toString());
        cv.put(DBHelper.KEY_TNotes, tNote.toString());
        cv.put(DBHelper.KEY_DATE, datenow.format(date));
        database.insert(DBHelper.TABLE_NOTES, null, cv);
    }

    public void updNote(Context context, String lNote, String tNote, String id_Note, SQLiteDatabase database)
    {
        final Date date = new Date();
        final SimpleDateFormat datenow = new SimpleDateFormat("dd.MM.yyyy");
        ContentValues cv = new ContentValues();
        cv.put(DBHelper.KEY_LNotes, lNote);
        cv.put(DBHelper.KEY_TNotes, tNote);
        cv.put(DBHelper.KEY_DATE, datenow.format(date));
        int updCount = database.update(DBHelper.TABLE_NOTES, cv, DBHelper.KEY_ID + "= ?", new String[]{id_Note});
    }

    public int[] indexTaker(SQLiteDatabase database)
    {
        Cursor cursor = database.query(DBHelper.TABLE_NOTES, null, null, null,null,null,null);
        int[] arrayIndex = new int[4];
        arrayIndex[0] = cursor.getColumnIndex(DBHelper.KEY_ID);
        arrayIndex[1] = cursor.getColumnIndex(DBHelper.KEY_LNotes);
        arrayIndex[2] = cursor.getColumnIndex(DBHelper.KEY_TNotes);
        arrayIndex[3] = cursor.getColumnIndex(DBHelper.KEY_DATE);

        return arrayIndex;
    }

    public ArrayList<NoteModel> noteView(SQLiteDatabase database) {
        ArrayList<NoteModel> notes = new ArrayList<>();
        Cursor cursor = database.query(DBHelper.TABLE_NOTES, null, null, null, null, null, null);

        int[] arrayIndex;
        if (cursor.moveToFirst()) {
            do {
                arrayIndex = indexTaker(database);
                notes.add(new NoteModel(cursor.getString(arrayIndex[1]), null, cursor.getString(arrayIndex[3])));
            } while (cursor.moveToNext());
        }
        return notes;
    }

}
