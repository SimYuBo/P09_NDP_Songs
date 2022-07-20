package com.myapplicationdev.android.p09_ndp_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle;
    EditText etSingers;
    EditText etYear;
    Button btnInsert;
    Button btnShowList;
    RadioGroup rgStars;
    ArrayList<Song> alSong;
    ArrayAdapter<Song> aaSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(String.valueOf(etYear.getText()));
                int stars = 0; // Temp
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(title, singers, year, stars);

                if (inserted_id != -1) {
                    alSong.clear();
                    alSong.addAll(dbh.getAllSongs());
                    aaSong.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Song target = alSong.get(0);

                Intent i = new Intent(MainActivity.this,
                        SongListActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }
        });
    }
}