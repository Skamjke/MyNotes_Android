package com.example.mynotes.Interface;

import android.content.Context;

public interface IEditNotePresenter {
    void onClickSaveUpd(Context context, String lNote, String tNote, String id_note, int id);
}
