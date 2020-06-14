package com.example.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class edit_note extends AppCompatActivity implements View.OnClickListener {

    TextView lNote, tNote;
    DBHelper dbHelper;
    String LNote,TNote,idNote;
    Button saveEdit, backToMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        lNote = (TextView)findViewById(R.id.label_note);
        tNote = (TextView)findViewById(R.id.text_note);

        saveEdit = (Button)findViewById(R.id.save_edit);
        saveEdit.setOnClickListener(edit_note.this);

        backToMain = (Button)findViewById(R.id.backBtn);
        backToMain.setOnClickListener(edit_note.this);

        Intent intent = getIntent();
         LNote = intent.getStringExtra("label");
         TNote = intent.getStringExtra("text");
         idNote = intent.getStringExtra("note_id");

        lNote.setText(LNote);
        tNote.setText(TNote);

        dbHelper = new DBHelper(this);

        
    }

    public void onClick(View v)
    {
        String lNoteCheck = lNote.getText().toString();
        String tNoteCheck = tNote.getText().toString();
        ContentValues cv = new ContentValues();
        switch (v.getId())
        {
            case R.id.save_edit:
                if (idNote.equalsIgnoreCase(""))
                {
                    break;
                }

                if (lNoteCheck.length() > 0)
                {
                    final Date date = new Date();
                    final SimpleDateFormat datenow = new SimpleDateFormat("dd.MM.yyyy");

                    SQLiteDatabase database = dbHelper.getWritableDatabase();

                    cv.put(DBHelper.KEY_LNotes, lNoteCheck);
                    cv.put(DBHelper.KEY_TNotes, tNoteCheck);
                    cv.put(DBHelper.KEY_DATE, datenow.format(date));
                    int updCount = database.update(DBHelper.TABLE_NOTES, cv, DBHelper.KEY_ID + "= ?", new String[]{idNote});
                    database.close();
                    Toast toast = Toast.makeText(getApplicationContext(), "Запись изменена!", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                }else {Toast toast = Toast.makeText(getApplicationContext(), "Название заметки отстуствует!", Toast.LENGTH_SHORT); toast.show();};
                break;

            case R.id.backBtn:
                finish();
                break;
        }
    }
}