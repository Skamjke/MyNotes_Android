package com.example.mynotes.Presenter;

import android.content.Context;

import com.example.mynotes.Interface.IEditNotePresenter;
import com.example.mynotes.Interface.IEditNoteView;
import com.example.mynotes.Interface.INoteModel;
import com.example.mynotes.Model.NoteModel;
import com.example.mynotes.R;

public class EditNotePresenter implements IEditNotePresenter {

    INoteModel iMainModel;
    IEditNoteView iEditNoteView;

    public EditNotePresenter(IEditNoteView _iEditNoteView)
    {
        iEditNoteView = _iEditNoteView;
        iMainModel = new NoteModel();
    }



    @Override
    public void onClickSaveUpd(Context context, String lNote, String tNote, String id_note, int id) {

        switch (id)
        {
            case R.id.save_edit:
                if (lNote.length() > 0)
                {
                    iMainModel.onUpdateNote(context, lNote, tNote, id_note);
                    iEditNoteView.showUpdateToast();
                    iEditNoteView.openListView();
                }else { iEditNoteView.ErrorNullName();};
                break;

            case R.id.backBtn:
                iEditNoteView.openListView();
                break;
        }

    }
}
