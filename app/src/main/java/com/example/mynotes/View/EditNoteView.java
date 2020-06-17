package com.example.mynotes.View;

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

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteView extends AppCompatActivity implements View.OnClickListener {

    TextView lNote, tNote;
    DBHelper dbHelper;
    String LNote,TNote, idNote;
    Button saveEdit, backToMain;
    Context ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_note_layout);

        ctx = this.getApplicationContext();

        lNote = (TextView)findViewById(R.id.label_note);
        tNote = (TextView)findViewById(R.id.text_note);

        saveEdit = (Button)findViewById(R.id.save_edit);
        saveEdit.setOnClickListener(EditNoteView.this);

        backToMain = (Button)findViewById(R.id.backBtn);
        backToMain.setOnClickListener(EditNoteView.this);

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
        String LNote = lNote.getText().toString();
        String TNote = tNote.getText().toString();
        ContentValues cv = new ContentValues();
        switch (v.getId())
        {
            case R.id.save_edit:
                if (idNote.equalsIgnoreCase(""))
                {
                    break;
                }

                if (LNote.length() > 0)
                {
                    SQLiteDatabase database = dbHelper.getWritableDatabase();
                    dbHelper.updNote(ctx, LNote, TNote, idNote, database);
                    dbHelper.close();
                    finish();
                }else {Toast toast = Toast.makeText(getApplicationContext(), "Название заметки отстуствует!", Toast.LENGTH_SHORT); toast.show();};
                break;

            case R.id.backBtn:
                finish();
                break;
        }
    }
}