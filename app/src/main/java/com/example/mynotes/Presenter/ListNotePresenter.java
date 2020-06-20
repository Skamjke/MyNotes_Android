package com.example.mynotes.Presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mynotes.Interface.IDetailNoteView;
import com.example.mynotes.Interface.IListNotePresenter;
import com.example.mynotes.Interface.IListNoteView;
import com.example.mynotes.Interface.IMainModel;
import com.example.mynotes.Model.MainModel;
import com.example.mynotes.View.DetailNoteView;
import com.example.mynotes.View.EditNoteView;

public class ListNotePresenter implements IListNotePresenter {

    IMainModel iMainModel;
    IListNoteView iListNoteView;
    IDetailNoteView iDetailNoteView;

    public ListNotePresenter(IListNoteView _iIListNoteView)
    {
        iListNoteView = _iIListNoteView;
        iMainModel = new MainModel();
    }

    @Override
    public void onLoadNotes(Context context, ListView nl) {
        iMainModel.onLoadListNotes(context, nl);
    }

    @Override
    public void onItemClick(Context context, int Position) {
        String[] Data = iMainModel.onTakeData(context, Position);
        iListNoteView.startActivity(Data, DetailNoteView.class);
    }

    @Override
    public void onLongItemClick(Context context, int Position) {
        String[] Data = iMainModel.onTakeData(context, Position);
        iListNoteView.startActivity(Data, EditNoteView.class);
    }


}
