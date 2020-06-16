package com.example.mynotes.View;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotes.Helpers.DBHelper;
import com.example.mynotes.R;

public class DetailNoteView extends AppCompatActivity implements View.OnClickListener {

    TextView lableView,textView;
    Button delBtn;
    DBHelper dbHelper;

    public void onClick(View v)
    {
        dbHelper = new DBHelper(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        Intent intent = getIntent();
        int id_note = intent.getIntExtra("note_id", 0);
        int delCount = database.delete(DBHelper.TABLE_NOTES, DBHelper.KEY_ID + "=" + id_note, null);
        Toast toast = Toast.makeText(getApplicationContext(), "Запись удалена", Toast.LENGTH_SHORT);
        toast.show();

        Intent backActivity = new Intent(this, ListNoteView.class);
        startActivity(backActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note_layout);

        delBtn = (Button)findViewById(R.id.delBtn);
        delBtn.setOnClickListener(DetailNoteView.this);

        lableView = (TextView)findViewById(R.id.label_note);
        textView = (TextView)findViewById(R.id.text_note);

        Intent intent = getIntent();

        String label_note = intent.getStringExtra("label");
        String text_note = intent.getStringExtra("text");

        lableView.setText(label_note);
        textView.setText(text_note);

    }
}