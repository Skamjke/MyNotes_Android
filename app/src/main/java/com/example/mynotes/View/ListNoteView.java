package com.example.mynotes.View;

import java.util.ArrayList;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.MainContract;
import com.example.mynotes.Model.NotesAdapter;
import com.example.mynotes.Model.NotesArray;
import com.example.mynotes.R;

public class ListNoteView extends AppCompatActivity implements View.OnClickListener {

    private Button adNote;
    public int pos;
    NotesAdapter noteAdapter;
    DBHelper dbHelper;

    private MainContract.Presenter mPresenter;

    public void onClick(View v)
    {
        Intent addNotesint = new Intent(this, AddNoteView.class);
        startActivity(addNotesint);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note_layout);


        fill_list_note();
    }

    public void fill_list_note()
    {
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        ArrayList<NotesArray> notes = dbHelper.noteView(database);
        noteAdapter = new NotesAdapter(this, notes);
        ListView nl = (ListView) findViewById(R.id.nl);

        adNote = (Button) findViewById(R.id.addNotes);
        adNote.setOnClickListener(this);

        nl.setAdapter(noteAdapter);

        nl.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            SQLiteDatabase database = dbHelper.getReadableDatabase();
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Cursor cursor = database.query(DBHelper.TABLE_NOTES, null,null,null,null,null,null);
                cursor.moveToPosition(position);
                int[] arrayIndex = dbHelper.indexTaker(database);
                pos = position;
                Intent intent = new Intent(ListNoteView.this, DetailNoteView.class);
                intent.putExtra("note_id", cursor.getString(arrayIndex[0]));
                intent.putExtra("label", cursor.getString(arrayIndex[1]));
                intent.putExtra("text", cursor.getString(arrayIndex[2]));
                startActivity(intent);
                dbHelper.close();
            }
        });
        nl.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            SQLiteDatabase database = dbHelper.getReadableDatabase();
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = database.query(DBHelper.TABLE_NOTES, null,null,null,null,null,null);
                pos = position;
                cursor.moveToPosition(pos);
                int[] arrayIndex = dbHelper.indexTaker(database);
                Intent intent = new Intent(ListNoteView.this, EditNoteView.class);
                intent.putExtra("note_id", cursor.getString(arrayIndex[0]));
                intent.putExtra("label", cursor.getString(arrayIndex[1]));
                intent.putExtra("text", cursor.getString(arrayIndex[2]));
                startActivity(intent);
                dbHelper.close();
                return false;
            }
        });
    }
    public void onRestart()
    {
        super.onRestart();
        setContentView(R.layout.list_note_layout);
        fill_list_note();
    }
}
