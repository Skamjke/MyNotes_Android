package com.example.mynotes;

import com.example.mynotes.Helpers.NotesArray;

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

    interface Model
    {
        ArrayList<NotesArray> loadNote();
    }
}
