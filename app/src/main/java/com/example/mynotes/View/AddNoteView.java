package com.example.mynotes.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mynotes.Interface.IAddNoteView;
import com.example.mynotes.Presenter.AddNotePresenter;
import com.example.mynotes.Interface.IAddNotePresenter;
import com.example.mynotes.R;

public class AddNoteView extends AppCompatActivity implements IAddNoteView, View.OnClickListener, DialogInterface.OnClickListener {

    private IAddNotePresenter iaddNotePresenter;
    private Button saveBtn, backToMain;
    private EditText LNote;
    private EditText TNote;
    private String lNote, tNote;

    @Override
    public void ErrorNullName() {
        Toast toast = Toast.makeText(this, "Поле название записи не заполнено!", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showSaveToast() {
        Toast toast = Toast.makeText(this, "Запись сохранена!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openListNoteView() {
        finish();
    }


    @Override
    public void onClick(View v) {
        lNote = LNote.getText().toString();
        tNote = TNote.getText().toString();
        iaddNotePresenter.onClickSaveNote(this, lNote, tNote, v.getId());
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
        iaddNotePresenter = new AddNotePresenter(this);

    }

    public void AlertDLG () {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Сохранить заметку?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", this);
        builder.setNegativeButton("Нет", this);
        AlertDialog alert = builder.create();
        alert.setTitle("Закрытие формы");
        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which)
        {
            case -1:
                iaddNotePresenter.onClickSaveNote(this, lNote, tNote, R.id.save_edit);
                break;
            case -2:
                openListNoteView();
                break;
        }
    }
}