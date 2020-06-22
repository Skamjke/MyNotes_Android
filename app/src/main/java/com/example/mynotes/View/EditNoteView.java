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
import com.example.mynotes.Interface.IEditNotePresenter;
import com.example.mynotes.Interface.IEditNoteView;
import com.example.mynotes.Presenter.EditNotePresenter;
import com.example.mynotes.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditNoteView extends AppCompatActivity implements IEditNoteView, View.OnClickListener {

    TextView lNote, tNote;
    String LNote,TNote, idNote;
    Button saveEdit, backToMain;
    Context ctx;
    IEditNotePresenter iEditNotePresenter;

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

        iEditNotePresenter = new EditNotePresenter(this);

    }

    @Override
    public void onClick(View v)
    {
        String _LNote = lNote.getText().toString();
        String _TNote = tNote.getText().toString();

        iEditNotePresenter.onClickSaveUpd(this, _LNote, _TNote, idNote, v.getId());
    }

    @Override
    public void showUpdateToast() {
        Toast toast = Toast.makeText(this, "Запись изменена!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void ErrorNullName() {
        Toast toast = Toast.makeText(this, "Поле название записи не заполнено!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openListView() {
        finish();
    }

}