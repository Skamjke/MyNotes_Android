package com.example.mynotes.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mynotes.R;
import com.example.mynotes.View.NoteArrayView;

import java.util.ArrayList;

public class ModelNoteAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<NoteArrayView> object;

    public ModelNoteAdapter(Context context, ArrayList<NoteArrayView> notes)
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
            view = lInflater.inflate(R.layout.list_item_layout, parent, false);
        }
        NoteArrayView n = getNotes(position);

        ((TextView) view.findViewById(R.id.lNote)).setText(n.name);
        ((TextView) view.findViewById(R.id.cDate)).setText(n.date);

        return view;
    }


    NoteArrayView getNotes(int position)
    {
        return ((NoteArrayView) getItem(position));
    }
}
