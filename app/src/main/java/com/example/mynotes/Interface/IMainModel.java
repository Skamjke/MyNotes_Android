package com.example.mynotes.Interface;

import android.content.Context;
import android.widget.ListView;

public interface IMainModel {
    void onSaveNote(Context context,String lNote, String tNote);
    void onDeleteNote(Context context, String id_note);
    void onUpdateNote(Context context, String lNote, String tNote, String id_note);
    void onLoadListNotes(Context context, ListView nl);
    String[] onTakeData(Context context, int position);
}
