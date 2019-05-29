package com.example.android.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartPageActivity extends AppCompatActivity {

    int number_of_questions = 5;
    EditText et1;
    Button start_button;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        et1 = findViewById(R.id.numOfQuestions);
        tv1 = findViewById(R.id.TRIVIA);
        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable edt) {
                if (edt.length() == 1 && edt.toString().equals("0"))
                    et1.setText("");
            }


            // ...
        });
        start_button = findViewById(R.id.startButton);
        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });
    }



    public void start(){
        number_of_questions = Integer.parseInt(et1.getText().toString());
        if(number_of_questions>20){
            AlertDialog alertDialog = new AlertDialog.Builder(StartPageActivity.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(number_of_questions + " seems too much! (try less than 20)");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else
        {
            Intent fresh_intent = new Intent(StartPageActivity.this,MainActivity.class);
            fresh_intent.putExtra("Num_of_questions", number_of_questions);
            startActivity(fresh_intent);
        }

    }

}
