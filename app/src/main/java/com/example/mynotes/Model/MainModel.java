package com.example.mynotes.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.Interface.IMainModel;
import com.example.mynotes.View.NoteArrayView;

import java.util.ArrayList;

public class MainModel implements IMainModel {


    @Override
    public void onSaveNote(Context context,String lNote, String tNote) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.addNote(context, lNote, tNote, database);
        dbHelper.close();
    }

    @Override
    public void onDeleteNote(Context context, String id_note) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int delCount = database.delete(DBHelper.TABLE_NOTES, DBHelper.KEY_ID + "=" + id_note, null);
        dbHelper.close();
    }

    @Override
    public void onUpdateNote(Context context, String lNote, String tNote, String id_note) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        dbHelper.updNote(context, lNote, tNote, id_note, database);
        dbHelper.close();
    }

    @Override
    public void onLoadListNotes(Context context, ListView nl) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ArrayList<NoteArrayView> notes = dbHelper.noteView(database);
        ModelNoteAdapter noteAdapter = new ModelNoteAdapter(context, notes);
        nl.setAdapter(noteAdapter);
        dbHelper.close();
    }

    @Override
    public String[] onTakeData(Context context, int position) {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(DBHelper.TABLE_NOTES, null,null,null,null,null,null);
        cursor.moveToPosition(position);
        int[] arrayIndex = dbHelper.indexTaker(database);
        String[] Data = new String[4];
        Data[0] = cursor.getString(arrayIndex[0]);
        Data[1] = cursor.getString(arrayIndex[1]);
        Data[2] = cursor.getString(arrayIndex[2]);
        return Data;
    }


}
