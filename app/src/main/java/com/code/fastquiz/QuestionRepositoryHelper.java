package com.code.fastquiz;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class QuestionRepositoryHelper extends AppCompatActivity {
    private ArrayList<Question> list_questions;

    public ArrayList<Question> readQuestionRepository(String string){

        try {
            JSONArray jsonArray = new JSONArray(string);

            ArrayList<Question> list_questions = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonQuestion = jsonArray.getJSONObject(i);
                
                String s_question = jsonQuestion.getString("question");
                boolean image = jsonQuestion.getBoolean("image");

                Question question;
                if(image){
                    String path = jsonQuestion.getString("path");
                    question = new Question(s_question,image,path);
                }else{
                    question = new Question(s_question);
                }

                JSONArray answersArray = jsonQuestion.getJSONArray("answers");
                for (int n = 0; n < answersArray.length(); n++) {
                    JSONObject jsonAnswer = answersArray.getJSONObject(n);
                    String answer = jsonAnswer.getString("answer");
                    boolean isCorrect = jsonAnswer.getBoolean("isCorrect");

                    question.addAnswer(answer, isCorrect);
                }
                list_questions.add(question);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Question> getQuestion(int numquest, boolean withImages) {
        if (numquest <= this.list_questions.size()) {
            ArrayList<Question> arrayQuestions;
            Random rnd = new Random(System.currentTimeMillis() * 17000);
            Collections.shuffle(this.list_questions,rnd);
            if(withImages) {
                arrayQuestions= new ArrayList<>(this.list_questions.subList(0, numquest));
            }else{
                arrayQuestions = new ArrayList<>();
                Iterator<Question> it = this.list_questions.iterator();
                int cont = 0;
                while(it.hasNext() && cont<numquest){
                    Question q = it.next();
                    if(!q.isImage())
                        arrayQuestions.add(q);
                    cont++;
                }
            }
            Random rnd2 = new Random(System.currentTimeMillis() * 23000);
            Collections.shuffle(arrayQuestions,rnd2);
            return arrayQuestions;
        } else {
            System.err.println("Se ha producido un error. El número de preguntas almacenado es inferior al solicitado.");
            return null;
        }
    }
}
