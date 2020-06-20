package com.example.mynotes.View;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.mynotes.Interface.IListNotePresenter;
import com.example.mynotes.Interface.IListNoteView;
import com.example.mynotes.Model.ModelNoteAdapter;
import com.example.mynotes.Presenter.ListNotePresenter;
import com.example.mynotes.R;

public class ListNoteView extends AppCompatActivity implements View.OnClickListener, IListNoteView, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private Button addNote;
    IListNotePresenter iListNotePresenter;
    private ListView nl;
    private ModelNoteAdapter noteAdapter;
    private ArrayList<NoteArrayView> notes;



    @Override
    public void onClick(View v)
    {
        Intent intent = new Intent(this, AddNoteView.class);
        startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_note_layout);
        nl = (ListView) findViewById(R.id.nl);
        nl.setOnItemClickListener(this);
        nl.setOnItemLongClickListener(this);
        iListNotePresenter = new ListNotePresenter(this);
        iListNotePresenter.onLoadNotes(this, nl);

        addNote = (Button) findViewById(R.id.addNotes);
        addNote.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        iListNotePresenter.onItemClick(this, position, DetailNoteView.class);
    }

    @Override
    public void StartActivity(Intent intent) {
        startActivity(intent);
    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        iListNotePresenter.onLongItemClick(this, position, EditNoteView.class);
        return false;
    }

    public void onRestart() {
        super.onRestart();
        setContentView(R.layout.list_note_layout);
        nl = (ListView) findViewById(R.id.nl);
        nl.setOnItemClickListener(this);
        nl.setOnItemLongClickListener(this);
        iListNotePresenter.onLoadNotes(this, nl);

    }

}
