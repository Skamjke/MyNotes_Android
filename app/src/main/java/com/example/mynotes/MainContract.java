package com.example.mynotes;

import com.example.mynotes.Model.NotesArray;

import java.util.ArrayList;

public interface MainContract {
    interface View
    {
        void showData(ArrayList<NotesArray> notesArrays);
    }

    interface Presenter
    {
        void activityOnLoad();
    }

    interface Repository
    {
        ArrayList<NotesArray> loadNote();
    }
}
