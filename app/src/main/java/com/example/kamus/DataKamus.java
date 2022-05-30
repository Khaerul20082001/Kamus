package com.example.kamus;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataKamus extends SQLiteOpenHelper {
    public DataKamus(Context context){
        super(context, Database_Name, null, 1);
    }

    public void createTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS kamus");
        db.execSQL("CREATE TABLE IF NOT EXISTS kamus(id integer primary key autoincrement, inggris TEXT, indonesia TEXT);");
    }


    public void generateData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(INGGRIS, "run");
        cv.put(INDONESIA, "lari");
        db.insert("kamus", INGGRIS, cv);
    }
    private static final String Database_Name="dbkamus";
    private static final String INGGRIS="inggris";
    private static final String INDONESIA = "indonesia";
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }
}


