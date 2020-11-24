package com.code.fastquiz;

import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class QuestionRepositoryHelper extends AppCompatActivity {

    public ArrayList<Question> readQuestionRepository(String json, boolean withImages){

        try {
            JSONArray jsonArray = new JSONArray(json);

            ArrayList<Question> list_questions = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonQuestion = jsonArray.getJSONObject(i);

                String s_question = jsonQuestion.getString("question");
                boolean image = jsonQuestion.getBoolean("image");

                Question question;

                if(withImages && image){
                    String path = jsonQuestion.getString("path");
                    question = new Question(s_question,image,path);
                }else if (withImages || !image){
                    question = new Question(s_question);
                }else{
                    question = null;
                }

                if (question != null) {
                    JSONArray answersArray = jsonQuestion.getJSONArray("answers");
                    for (int n = 0; n < answersArray.length(); n++) {
                        JSONObject jsonAnswer = answersArray.getJSONObject(n);
                        String answer = jsonAnswer.getString("answer");
                        boolean isCorrect = jsonAnswer.getBoolean("isCorrect");

                        question.addAnswer(answer, isCorrect);
                    }
                    list_questions.add(question);
                }
            }
            Random rnd = new Random(System.currentTimeMillis() * System.currentTimeMillis());
            Collections.shuffle(list_questions,rnd);
            return list_questions;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
