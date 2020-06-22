package com.example.mynotes.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.Interface.INoteModel;
import com.example.mynotes.Adapters.NoteAdapter;

import java.util.ArrayList;

public class NoteModel implements INoteModel {


    public String name, date;

    public NoteModel()
    {

    }

    public NoteModel(String _name, String _date)
    {
        name = _name;
        date = _date;
    }

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
        database.delete(DBHelper.TABLE_NOTES, DBHelper.KEY_ID + "=" + id_note, null);
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
        ArrayList<NoteModel> notes = dbHelper.noteView(database);
        NoteAdapter noteAdapter = new NoteAdapter(context, notes);
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
