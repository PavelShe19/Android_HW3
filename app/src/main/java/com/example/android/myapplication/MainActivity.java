package com.example.android.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mySong;
    TextView question;
    ListView answersList;
    ArrayAdapter<String> adapter;
    Server.Question currentQuestion;
    int max_num_of_questions;
    int current_question_number;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        score = 0;
        current_question_number = 1;
        max_num_of_questions = intent.getIntExtra("Num_of_questions",5);
        setContentView(R.layout.activity_main);
        mySong=MediaPlayer.create(MainActivity.this,R.raw.elevator2);
        question = findViewById(R.id.text);
        answersList = findViewById(R.id.answersList);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                new ArrayList<String>());
        getQuestion();
        answersList.setAdapter(adapter);
        answersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String chosen = (String)adapterView.getItemAtPosition(position);
                if(currentQuestion.correctAnswer.equals(chosen)){
                    Toast.makeText(adapterView.getContext(), "right!", Toast.LENGTH_SHORT).show();
                    score++;
                } else{
                    Toast.makeText(adapterView.getContext(), "wrong!", Toast.LENGTH_SHORT).show();
                }
                current_question_number++;
                if(current_question_number <= max_num_of_questions) {
                    getQuestion();
                }
                else
                {

                    if(score == max_num_of_questions){
                        //open max score page

                        Intent fresh_intent = new Intent(MainActivity.this,MaxActivity.class);
                        fresh_intent.putExtra("score", score);
                        fresh_intent.putExtra("MaxScore", max_num_of_questions);
                        startActivity(fresh_intent);
                    }
                    else{
                        //open normal score page

                        Intent fresh_intent = new Intent(MainActivity.this,EndActivity.class);
                        fresh_intent.putExtra("score", score);
                        fresh_intent.putExtra("MaxScore", max_num_of_questions);
                        startActivity(fresh_intent);

                    }
                }
            }
        });


    }
    @Override
    protected void onPause() {
        super.onPause();
        mySong.release();
    }

    public void getQuestion() {
        mySong.start();
        Server.getTriviaQuestion(new Server.HandleQuestion() {
            @Override
            public void handleQuestion(Server.Question q) {
                currentQuestion = q;
                question.setText(Html.fromHtml(q.question));
                adapter.clear();
                for(String a: q.answers){
                    adapter.add(a);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
