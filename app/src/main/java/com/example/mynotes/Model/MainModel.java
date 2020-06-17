package com.example.mynotes.Model;

import android.database.sqlite.SQLiteDatabase;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.View.ListNoteView;
import com.example.mynotes.MainContract;
import com.example.mynotes.View.NoteArrayView;

import java.util.ArrayList;

public class MainModel implements MainContract.Model {
    DBHelper dbHelper;
    ListNoteView mainActivity;

    @Override
    public ArrayList<NoteArrayView> loadNote()
    {
        mainActivity = new ListNoteView();
        dbHelper = new DBHelper(mainActivity);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        return dbHelper.noteView(database);
    }
}
