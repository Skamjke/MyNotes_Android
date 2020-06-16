package com.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MainRepository implements MainContract.Repository {
    DBHelper dbHelper;
    MainActivity mainActivity;

    @Override
    public ArrayList<NotesArray> loadNote()
    {
        mainActivity = new MainActivity();
        dbHelper = new DBHelper(mainActivity);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return dbHelper.noteView(database);
    }
}
