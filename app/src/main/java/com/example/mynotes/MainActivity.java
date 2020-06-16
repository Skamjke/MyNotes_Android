package com.example.mynotes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button adNote;
    public int pos;
    NotesAdapter noteAdapter;
    DBHelper dbHelper;

    private MainContract.Presenter mPresenter;

    public void onClick(View v)
    {
        Intent addNotesint = new Intent(this, Notes.class);
        startActivity(addNotesint);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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
                int idIndex = cursor.getColumnIndex(DBHelper.KEY_ID);
                int[] arrayIndex = dbHelper.indexTaker(database);
                pos = position;
                Intent intent = new Intent(MainActivity.this, detailActivity.class);
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
                Intent intent = new Intent(MainActivity.this, edit_note.class);
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
        setContentView(R.layout.activity_main);
        fill_list_note();
    }
}
