package com.example.kamus;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddData extends Activity {
    private SQLiteDatabase db = null;
    private Cursor kamusCursor = null;
    private EditText txtInggris;
    private EditText txtIndonesia;
    private DataKamus dataKamus = null;
    public static final String INGGRIS = "inggris";
    public static final String INDONESIA = "indonesia";
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dataKamus = new DataKamus(this);
        db = dataKamus.getWritableDatabase();
        dataKamus.createTable(db);
        dataKamus.generateData(db);

        setContentView(R.layout.simpan);
        txtInggris = (EditText) findViewById(R.id.txteng);
        txtIndonesia = (EditText) findViewById(R.id.txtina);
    }

    public void SimpanData(View view){
        db.execSQL("insert into kamus(inggris, indonesia)" + "values('"+txtInggris.getText().toString()+"',"+"'"+txtIndonesia.getText().toString()+"')");
        Toast.makeText(getBaseContext(), "data sudah disimpan", Toast.LENGTH_LONG).show();
    }
}
