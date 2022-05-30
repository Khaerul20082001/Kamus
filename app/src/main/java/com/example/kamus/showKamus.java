package com.example.kamus;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class showKamus extends Activity {
    private SQLiteDatabase db = null;
    private Cursor kamusCursor = null;
    private EditText txtInggris;
    private EditText txtIndonesia;
    private DataKamus datakamus = null;
    public static final String INGGRIS = "inggris";
    public static final String INDONESIA = "indonesia";

    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        datakamus = new DataKamus(this);
        db = datakamus.getWritableDatabase();
        datakamus.createTable(db);
        datakamus.generateData(db);

        setContentView(R.layout.activity_main);
        txtInggris = (EditText) findViewById(R.id.txtinggris);
        txtIndonesia = (EditText) findViewById(R.id.txtindonesia);
    }

    public void getTerjemahkan(View view){
        String result = "";
        String englishword = txtInggris.getText().toString();
        kamusCursor = db.rawQuery("SELECT ID, INGGRIS, INDONESIA " + "FROM kamus where INGGRIS = '" + englishword + "' ORDER BY INGGRIS", null);

        if(kamusCursor.moveToFirst()){
            result = kamusCursor.getString(2);
            for (; !kamusCursor.isAfterLast(); kamusCursor.moveToNext()){
                result = kamusCursor.getString(2);
            }
        }

        if (result.equals("")){
            result = "terjemahan Not Found";
        }
        txtIndonesia.setText(result);
    }

    public void AddData(View view){
        Intent intent = new Intent().setClass(this,AddData.class);
        startActivity(intent);
    }

    public void onDestroy(){
        super.onDestroy();
        kamusCursor.close();
        db.close();
    }
}
