package com.example.mynotes.Interface;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public interface IListNotePresenter {
    void onLoadNotes(Context context, ListView nl);
    void onItemClick(Context context, int Position, Intent intent);
    void onLongItemClick(Context context, int Position, Intent intent);
}
