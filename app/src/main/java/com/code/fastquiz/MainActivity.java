package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
 // ArrayList<Question> arrayQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = findViewById(R.id.button_start_game);

        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
               /* arrayQuestions = new ArrayList<Question>();
                Initializer ini = new Initializer();
                arrayQuestions = ini.getQuestion(5); */

                Intent activity = new Intent(MainActivity.this,Game.class);
                // activity.putExtra("arrayQuestions", arrayQuestions);
                startActivity(activity);
            }
        });


    }

}