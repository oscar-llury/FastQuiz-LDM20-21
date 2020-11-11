package com.code.fastquiz;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;

public class QuestionRepositoryHelper extends AppCompatActivity {
    private ArrayList<Question> list_questions;

    public QuestionRepositoryHelper(){
        /*try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("hola.txt"));
            BufferedReader fin = new BufferedReader(archivo);
            String texto = fin.readLine() + "\n";
            String textocomp = "";

            while(texto != null){
                textocomp = textocomp + texto + "\n";
                texto = fin.readLine();
            }
            fin.close();
            archivo.close();
            textView8.setText(textocomp);
        } catch (
                IOException e) {
            Log.e("Ficheros", "Error al leer fichero desde memoria interna");
        }

         */
        try {
            OutputStreamWriter fout =
                    new OutputStreamWriter(
                            openFileOutput("questions.txt", Context.MODE_PRIVATE));
            fout.write("¿Cuál es la capital de Mongolia?" + "\n");
            fout.write("Estambul" + "\n");
            fout.write("Ulan Bator" + "\n");
            fout.write("Madrid" + "\n");
            fout.write("Tokio" + "\n");
            fout.flush();
            fout.close();
            Toast.makeText(this,"Archivo escrito",Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.e("Ficheros", "Error al escribir fichero en mem.int");
            Toast.makeText(this,"Error en el archivo",Toast.LENGTH_SHORT).show();
        }
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
