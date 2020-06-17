package com.example.mynotes.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.R;

public class AddNoteView extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    Context ctx;
    DBHelper dbHelper;
    SQLiteDatabase database;
    private Button saveBtn, backToMain;
    private EditText LNote;
    private EditText TNote;
    private String lNote, tNote;

    public void onClick(View v)
    {
         lNote = LNote.getText().toString();
         tNote = TNote.getText().toString();

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
                        AlertDLG();
                    } else
                        {
                            finish();
                        }

                break;
        }
        dbHelper.close();
    }

    public void AlertDLG () {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNoteView.this);
        builder.setMessage("Сохранить заметку?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", this);
        builder.setNegativeButton("Нет", this);
        AlertDialog alert = builder.create();
        alert.setTitle("Закрытие формы");
        alert.show();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_layout);

        backToMain = (Button)findViewById(R.id.backBtn);
        backToMain.setOnClickListener(AddNoteView.this);

        saveBtn = (Button)findViewById(R.id.save_edit);
        saveBtn.setOnClickListener(AddNoteView.this);

        LNote = (EditText)findViewById(R.id.label_note);
        TNote = (EditText)findViewById(R.id.text_note);

        ctx = this.getApplicationContext();

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    switch (which)
    {
        case -1:
            if (database != dbHelper.getWritableDatabase())
                database = dbHelper.getWritableDatabase();
            dbHelper.addNote(ctx, lNote, tNote, database);
            dbHelper.close();
            finish();
            break;
        case -2:
            finish();
            break;
    }
    }


}