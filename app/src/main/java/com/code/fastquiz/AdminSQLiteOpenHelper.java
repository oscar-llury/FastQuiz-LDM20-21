package com.code.fastquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase fastQuiz_bbdd) {
        //crear la base de datos
        //baseDatos.getPath();

        fastQuiz_bbdd.execSQL("create table ranking(name varchar(50) primary key, score int)");
        //fastQuiz_bbdd.execSQL("create table questions(id primary key, question text, score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
