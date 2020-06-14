package com.example.mynotes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes extends AppCompatActivity implements View.OnClickListener {

    DBHelper dbHelper;
    SQLiteDatabase database;
    private Button saveBtn, checkBtn, backToMain;
    private EditText labelnote;
    private EditText textnote;

    public void onClick(View v)
    {
        final String namenote = labelnote.getText().toString();
        final String note = textnote.getText().toString();


        final Date date = new Date();
        final SimpleDateFormat datenow = new SimpleDateFormat("dd.MM.yyyy");

        switch (v.getId())
        {
            case R.id.save_edit:
                if((namenote.length() > 0 && note.length() > 0) || (namenote.length() > 0 && note.length() <= 0))
                {
                    if (database != dbHelper.getWritableDatabase())
                        database = dbHelper.getWritableDatabase();
                    ContentValues cv = new ContentValues();
                    cv.put(DBHelper.KEY_LNotes, namenote);
                    cv.put(DBHelper.KEY_TNotes, note);
                    cv.put(DBHelper.KEY_DATE, datenow.format(date));
                    database.insert(DBHelper.TABLE_NOTES, null, cv);
                    textnote.setText("");
                    labelnote.setText("");
                    Toast toast = Toast.makeText(getApplicationContext(), "Запись сохранена", Toast.LENGTH_SHORT);
                    toast.show();
                    dbHelper.close();
                    finish();
                }else {Toast toast = Toast.makeText(getApplicationContext(), "Поле название записи не заполнено!", Toast.LENGTH_SHORT); toast.show();}
                break;

            case R.id.backBtn:
                if (namenote.length() > 0 || note.length() > 0)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
                    builder.setMessage("Сохранить изменения?")
                            .setCancelable(false)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    if (database != dbHelper.getWritableDatabase()) database = dbHelper.getWritableDatabase();
                                    ContentValues cv = new ContentValues();
                                    cv.put(DBHelper.KEY_LNotes, namenote);
                                    cv.put(DBHelper.KEY_TNotes, note);
                                    cv.put(DBHelper.KEY_DATE, datenow.format(date));
                                    database.insert(DBHelper.TABLE_NOTES, null, cv);

                                    Toast toast = Toast.makeText(getApplicationContext(), "Запись сохранена", Toast.LENGTH_SHORT);
                                    toast.show();
                                    finish();
                                }

                            })
                            .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    textnote.setText("");
                                    labelnote.setText("");
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

        checkBtn = (Button)findViewById(R.id.checkbtn);
        checkBtn.setOnClickListener(this);

        labelnote = (EditText)findViewById(R.id.label_note);
        textnote = (EditText)findViewById(R.id.text_note);


        dbHelper = new DBHelper(this);
    }


}