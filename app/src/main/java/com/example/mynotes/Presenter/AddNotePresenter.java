package com.example.mynotes.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.example.mynotes.Interface.IAddNotePresenter;
import com.example.mynotes.Interface.INoteModel;
import com.example.mynotes.Model.NoteModel;
import com.example.mynotes.R;
import com.example.mynotes.Interface.IAddNoteView;

public class AddNotePresenter implements IAddNotePresenter, DialogInterface.OnClickListener {
    INoteModel iMainModel;
    IAddNoteView iAddNoteView;
    Context context;
    String lNote, tNote;

    public AddNotePresenter(IAddNoteView iaddNoteView)
    {
        iAddNoteView = iaddNoteView;
        iMainModel = new NoteModel();
    }

    @Override
    public void onClickSaveNote(Context _context, String _lNote, String _tNote, int id) {
        context = _context;
        lNote = _lNote;
        tNote = _tNote;
        switch (id) {
            case R.id.save_edit:
                if ((lNote.length() > 0 && tNote.length() > 0) || (lNote.length() > 0 && tNote.length() == 0)) {
                    iMainModel.onSaveNote(context, lNote, tNote);
                    iAddNoteView.showSaveToast();
                    iAddNoteView.openListNoteView();
                } else { iAddNoteView.ErrorNullName();}
                break;

            case R.id.backBtn:
                if (lNote.length() > 0) {
                    AlertDLG();
                } else {
                    iAddNoteView.openListNoteView();
                }
                break;
        }
    }

    public void AlertDLG () {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Сохранить заметку?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", this);
        builder.setNegativeButton("Нет", this);
        AlertDialog alert = builder.create();
        alert.setTitle("Закрытие формы");
        alert.show();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which)
        {
            case -1:
                iMainModel.onSaveNote(context, lNote, tNote);
                iAddNoteView.openListNoteView();
                break;
            case -2:
                iAddNoteView.openListNoteView();
                break;
        }
}
}
