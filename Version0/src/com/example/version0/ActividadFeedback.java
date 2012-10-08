package com.example.version0;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ActividadFeedback extends Activity {
	 private ArrayList<String> cursos;
	 public AdapterCursos dbramos = new AdapterCursos(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_feedback);
        cargarDatos();
        
        
        Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,cursos));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_feedback, menu);
        return true;
    }
    private void cargarDatos() {
    	
		cursos= new ArrayList<String>();
    	
    	dbramos.open();
        Cursor c = dbramos.getAllRecords();
        
        if (c.moveToFirst())
        {
            do {
                String curso = c.getString(1);
                //String idramo = c.getString(0);
                //Cursor notas = dbnotas.getNotas(idramo);
                //array_notas.add(notas);
                cursos.add(curso);
            } while (c.moveToNext());
        }
        dbramos.close();
            	
    
}
    
    public void enviarComentario(View view)
    {}

}