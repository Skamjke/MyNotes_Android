package com.example.mynotes;

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
