package com.myapplicationdev.android.p09_ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {

    EditText etID, etTitle, etSingers, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RadioGroup rgStars;
    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        rgStars = findViewById(R.id.rgStars);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("songData");

        etID.setText(data.get_id());
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(data.getYear());


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                data.setSong(etTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()), rgStars.getCheckedRadioButtonId());
                dbh.updateSong(data);
                dbh.close();

                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyActivity.this);
                dbh.deleteSong(data.get_id());

                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ModifyActivity.this,
                        SongListActivity.class);
                startActivity(i);
            }
        });
    }
}