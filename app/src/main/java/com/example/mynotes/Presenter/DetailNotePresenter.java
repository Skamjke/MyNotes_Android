package com.example.mynotes.Presenter;

import android.content.Context;

import com.example.mynotes.Interface.IDetailNotePresenter;
import com.example.mynotes.Interface.IDetailNoteView;
import com.example.mynotes.Interface.IMainModel;
import com.example.mynotes.Model.MainModel;

public class DetailNotePresenter implements IDetailNotePresenter {

    IMainModel mainModel;
    IDetailNoteView iDetailNoteView;

    public DetailNotePresenter(IDetailNoteView _iDetailNoteView)
    {
        iDetailNoteView = _iDetailNoteView;
        mainModel = new MainModel();
    }
    @Override
    public void onClickDelete(Context context, String id_note) {
        mainModel.onDeleteNote(context, id_note);
        iDetailNoteView.showDeleteToast();
    }
}
