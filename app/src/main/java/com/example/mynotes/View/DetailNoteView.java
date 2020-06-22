package com.example.mynotes.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.Interface.IDetailNotePresenter;
import com.example.mynotes.Interface.IDetailNoteView;
import com.example.mynotes.Presenter.DetailNotePresenter;
import com.example.mynotes.R;

public class DetailNoteView extends AppCompatActivity implements View.OnClickListener, IDetailNoteView {

    private String id_note, lNote, tNote;
    private TextView lableView,textView;
    Button delBtn;
    IDetailNotePresenter iDetailNotePresenter;

    public void onClick(View v)
    {
        iDetailNotePresenter.onClickDelete(this, id_note);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note_layout);

        delBtn = (Button)findViewById(R.id.delBtn);
        delBtn.setOnClickListener(DetailNoteView.this);

        lableView = (TextView)findViewById(R.id.label_note);
        textView = (TextView)findViewById(R.id.text_note);

        Intent intent = getIntent();
        id_note = intent.getStringExtra("note_id");
        lNote = intent.getStringExtra("label");
        tNote = intent.getStringExtra("text");

        lableView.setText(lNote);
        textView.setText(tNote);

        iDetailNotePresenter = new DetailNotePresenter(this);

    }

    @Override
    public void showDeleteToast() {
        Toast toast = Toast.makeText(getApplicationContext(), "Запись удалена", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }

}