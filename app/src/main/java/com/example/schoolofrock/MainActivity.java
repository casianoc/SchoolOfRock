package com.example.schoolofrock;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // declare EditText
    EditText voterEditText;

    // declare Spinners
    Spinner songSpinner;

    // declare Strings for data input by user
    String song;
    String voter;
    int vote;

    // declare DBHandler
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        voterEditText = (EditText) findViewById(R.id.voterEditText);
        songSpinner = (Spinner) findViewById(R.id.songSpinner);

        ArrayAdapter<CharSequence> songAdapter = ArrayAdapter.createFromResource(this, R.array.song, android.R.layout.simple_spinner_item);

        // further stylize ArrayAdapter with style defined by simple_spinner_dropdown_item
        songAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set ArrayAdapter on Spinner
        songSpinner.setAdapter(songAdapter);

        // set OnItemSelectedListener on Spinner
        songSpinner.setOnItemSelectedListener(this);

        // initialize DBHandler
        dbHandler = new DBHandler(this, null);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void like(MenuItem item) {
        voter = voterEditText.getText().toString();

        // trim Strings and see if any are equal to an empty String
        if ((voter.trim().equals("")) || (song.trim().equals(""))) {
            // if any are equal to an empty String, then that means
            // required data hasn't been input, so display a toast
            Toast.makeText(this, "Please select a song and enter your name!", Toast.LENGTH_LONG).show();
        } else {
            vote = 1;
            dbHandler.addSongVote(song, voter, vote);
            Toast.makeText("You liked " + song, Toast.LENGTH_LONG).show();

        }
        }

        public void dislike(MenuItem item) {
            voter = voterEditText.getText().toString();

            // trim Strings and see if any are equal to an empty String
            if ((voter.trim().equals("")) || (song.trim().equals(""))) {
                // if any are equal to an empty String, then that means
                // required data hasn't been input, so display a toast
                Toast.makeText(this, "Please select a song and enter your name!", Toast.LENGTH_LONG).show();
            } else {
                vote = 0;
                dbHandler.addSongVote(song, voter, vote);
                Toast.makeText("You disliked " + song, Toast.LENGTH_LONG).show();
            }
        }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        // check if it was the year Spinner that was selected
        if (adapterView.equals(songSpinner))
            // get item selected based on position, convert it to a String
            // and store it in String
            song = adapterView.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

