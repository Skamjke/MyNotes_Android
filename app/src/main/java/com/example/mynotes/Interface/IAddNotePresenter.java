package com.example.mynotes.Interface;

import android.content.Context;

public interface IAddNotePresenter {
    void onClickSaveNote(Context context,String lNote,String tNote,int id);
}
