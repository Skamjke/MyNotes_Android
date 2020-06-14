package com.example.mynotes;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<NotesArray> object;

    NotesAdapter(Context context, ArrayList<NotesArray> notes)
    {
        ctx = context;
        object = notes;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount()
    {
        return object.size();
    }

    @Override
    public Object getItem(int position)
    {
        return object.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if (view == null)
        {
            view = lInflater.inflate(R.layout.list_item_note, parent, false);
        }
        NotesArray n = getNotes(position);

        ((TextView) view.findViewById(R.id.lNote)).setText(n.name);
        ((TextView) view.findViewById(R.id.cDate)).setText(n.date);

        return view;
    }


    NotesArray getNotes(int position)
    {
        return ((NotesArray) getItem(position));
    }
}
