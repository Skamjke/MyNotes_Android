package com.example.mynotes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity implements View.OnClickListener {

    Context ctx;
    DBHelper dbHelper;
    SQLiteDatabase database;
    private Button saveBtn, backToMain;
    private EditText LNote;
    private EditText TNote;

    public void onClick(View v)
    {
         final String lNote = LNote.getText().toString();
         final String tNote = TNote.getText().toString();

        switch (v.getId())
        {
            case R.id.save_edit:
                if((lNote.length() > 0 && tNote.length() > 0) || (lNote.length() > 0 && tNote.length() <= 0))
                {
                    if (database != dbHelper.getWritableDatabase())
                        database = dbHelper.getWritableDatabase();
                    //Метод добавления записи в БД, описанный в классе DBHelper.
                    dbHelper.addNote(ctx, lNote, tNote, database);
                    //Закрытие Activity
                    finish();
                }else {Toast toast = Toast.makeText(getApplicationContext(), "Поле название записи не заполнено!", Toast.LENGTH_SHORT); toast.show();}
                break;

                case R.id.backBtn:
                    if (lNote.length() > 0)
                    {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
                    builder.setMessage("Сохранить изменения?");
                    builder.setCancelable(false);
                        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (database != dbHelper.getWritableDatabase())
                                    database = dbHelper.getWritableDatabase();
                                dbHelper.addNote(ctx, lNote, tNote, database);
                                dbHelper.close();
                                finish();
                            }

                        });
                        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.setTitle("Закрытие формы");
                        alert.show();
                } else
                {
                    finish();
                }

                break;
        }
        dbHelper.close();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        backToMain = (Button)findViewById(R.id.backBtn);
        backToMain.setOnClickListener(Notes.this);

        saveBtn = (Button)findViewById(R.id.save_edit);
        saveBtn.setOnClickListener(this);

        LNote = (EditText)findViewById(R.id.label_note);
        TNote = (EditText)findViewById(R.id.text_note);

        ctx = this.getApplicationContext();

        dbHelper = new DBHelper(this);
    }


}