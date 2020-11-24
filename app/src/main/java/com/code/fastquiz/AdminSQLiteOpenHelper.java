package com.code.fastquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
/**
 * Clase que crea la base de datos con SQLite
 *
 * @author Carlos González, Óscar Rivas
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /**
     * Este método devuelve la tabla del ranking de jugadores y su puntuación
     *
     *
     */
    @Override
    public void onCreate(SQLiteDatabase fastQuiz_bbdd) {

        fastQuiz_bbdd.execSQL("create table ranking(name varchar(50) primary key, score int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
