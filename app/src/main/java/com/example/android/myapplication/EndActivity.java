package com.example.android.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class EndActivity extends AppCompatActivity {

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

        AlertDialog alertDialog = new AlertDialog.Builder(EndActivity.this).create();
        alertDialog.setMessage("You scored: " + score + " Out of: " + MaxScore);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
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
        Intent fresh_intent = new Intent(EndActivity.this, StartPageActivity.class);

        startActivity(fresh_intent);
    }
}
