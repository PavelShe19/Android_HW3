package com.example.android.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MaxActivity extends AppCompatActivity {
    MediaPlayer mySong;
    int score;
    int MaxScore;
    Button start_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Intent intent = getIntent();
        score = intent.getIntExtra("score",0);
        MaxScore = intent.getIntExtra("MaxScore",0);
        mySong= MediaPlayer.create(MaxActivity.this,R.raw.queen);
        mySong.start();

        AlertDialog alertDialog = new AlertDialog.Builder(MaxActivity.this).create();
        alertDialog.setTitle("Congratulations!");
        alertDialog.setMessage("You scored: " + score + " Out of: " + MaxScore);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Awesome",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        start_button = findViewById(R.id.startButton);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }

    public void start() {
        mySong.stop();
        Intent fresh_intent = new Intent(MaxActivity.this, StartPageActivity.class);

        startActivity(fresh_intent);
    }
}
