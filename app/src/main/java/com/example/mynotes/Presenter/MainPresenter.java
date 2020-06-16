package com.example.mynotes.Presenter;

import com.example.mynotes.MainContract;
import com.example.mynotes.Model.MainModel;
import com.example.mynotes.Model.NotesArray;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Repository mRepository;

    private ArrayList<NotesArray> arrayNotes;

    public MainPresenter(MainContract.View mView)
    {
        this.mView = mView;
        this.mRepository = new MainModel();
    }

    public void activityOnLoad()
    {
        arrayNotes = mRepository.loadNote();
        mView.showData(arrayNotes);
    }
}
