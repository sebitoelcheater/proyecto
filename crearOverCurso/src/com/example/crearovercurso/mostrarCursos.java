package com.example.crearovercurso;

import android.app.Activity;
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

public class mostrarCursos extends Activity {
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarcursos);
        
        SQLiteDatabase db=openOrCreateDatabase("MyDB", MODE_PRIVATE, null);
        Cursor c = db.rawQuery("SELECT * from Cursos", null);
        Log.d("results", c.getString(c.getColumnIndex("nombre")));
        String cursitos = c.getString(c.getColumnIndex("nombre"));
        TextView resultado = (TextView)findViewById(R.id.resultado);
        resultado.setText("hola"+cursitos);
        db.close();
        
        
    }
    public void fin(View view)
    {
    	finish();
    }
}