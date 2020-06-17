package com.example.mynotes.Presenter;

import com.example.mynotes.MainContract;
import com.example.mynotes.Model.MainModel;
import com.example.mynotes.View.NoteArrayView;

import java.util.ArrayList;

public class ListNotePresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model mModel;

    private ArrayList<NoteArrayView> arrayNotes;

    public ListNotePresenter(MainContract.View mView)
    {
        this.mView = mView;
        this.mModel = new MainModel();
    }

    public void activityOnLoad()
    {
        arrayNotes = mModel.loadNote();
        mView.showData(arrayNotes);
    }
}
