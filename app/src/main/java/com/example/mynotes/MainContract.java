package com.example.mynotes;

import com.example.mynotes.View.NoteArrayView;

import java.util.ArrayList;

public interface MainContract {
    interface View
    {
        void showData(ArrayList<NoteArrayView> notesArrays);
    }

    interface Presenter
    {
        void activityOnLoad();
    }

    interface Model
    {
        ArrayList<NoteArrayView> loadNote();
    }
}
