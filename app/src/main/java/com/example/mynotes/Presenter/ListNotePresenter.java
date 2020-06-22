package com.example.mynotes.Presenter;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.example.mynotes.Interface.IListNotePresenter;
import com.example.mynotes.Interface.IListNoteView;
import com.example.mynotes.Interface.INoteModel;
import com.example.mynotes.Model.NoteModel;

public class ListNotePresenter implements IListNotePresenter {

    INoteModel iMainModel;
    IListNoteView iListNoteView;

    public ListNotePresenter(IListNoteView _iIListNoteView)
    {
        iListNoteView = _iIListNoteView;
        iMainModel = new NoteModel();
    }

    @Override
    public void onLoadNotes(Context context, ListView nl) {
        iMainModel.onLoadListNotes(context, nl);
    }

    @Override
    public void onItemClick(Context context, int Position, Intent intent) {
        String[] Data = iMainModel.onTakeData(context, Position);
        iListNoteView.StartActivity(intent, Data);
    }

    @Override
    public void onLongItemClick(Context context, int Position, Intent intent) {
        String[] Data = iMainModel.onTakeData(context, Position);
        iListNoteView.StartActivity(intent, Data);
    }


}
