package com.example.crearovercurso;

import android.app.Activity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class crearCurso extends Activity {
	
	private EditText nombrecursoo, profesorr, horarioo;
	String nombrecursooo, profesorrr, horariooo;
	    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crearelcurso);
       
    }
    
    public String getDateTime() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	return dateFormat.format(date);
    	}
    
    public void crear(View view){
    	String codigounico = getDateTime();
    	
        nombrecursoo=(EditText)findViewById(R.id.nombrecurso);
        profesorr=(EditText)findViewById(R.id.profesor);
        horarioo=(EditText)findViewById(R.id.horario);
        nombrecursooo = nombrecursoo.getText().toString();
        profesorrr = profesorr.getText().toString();
        horariooo = horarioo.getText().toString();
    	
        SQLiteDatabase db=openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Cursos (id VARCHAR, nombre VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Notas (id VARCHAR, clase VARCHAR, valor VARCHAR, nombre VARCHAR, fecha VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Profesores (id VARCHAR, nombre VARCHAR, email VARCHAR);");
        db.execSQL("CREATE TABLE IF NOT EXISTS Horario (inicio VARCHAR, fin VARCHAR, ubicacion VARCHAR);");

        db.execSQL("INSERT INTO Cursos VALUES('"+codigounico+"','"+nombrecursooo+"');");
        db.execSQL("INSERT INTO Profesores VALUES('sin id','"+profesorrr+"','sin email');");
        db.execSQL("INSERT INTO Horario VALUES('"+horariooo+"','"+horariooo+"','en ninguna parte');");
        

        db.close();
        Log.d("results", nombrecursooo);
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(codigounico);
        
        
        
        finish();
    }
}