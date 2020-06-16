package com.example.mynotes;

import java.util.ArrayList;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View mView;
    private MainContract.Repository mRepository;

    private ArrayList<NotesArray> arrayNotes;

    public MainPresenter(MainContract.View mView)
    {
        this.mView = mView;
        this.mRepository = new MainRepository();
    }

    public void activityOnLoad()
    {
        arrayNotes = mRepository.loadNote();
        mView.showData(arrayNotes);
    }
}
