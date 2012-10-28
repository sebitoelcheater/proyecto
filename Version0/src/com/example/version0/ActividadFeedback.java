package com.example.version0;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import com.example.controlador.*;
import com.example.data.*;
public class ActividadFeedback extends Activity {
	 private ArrayList<String> cursos;
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_feedback);
        Bundle b = getIntent().getExtras();
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
    	
		ArrayList<Curso> cursosComentables = Controlador.obtenerCursos(this); // ACA DEBERIA IR UN METODO LLAMADO OBTENERCURSOSCOMENTABLES... NO OLVIDAR PEDIRMELO!
    	cursos = new ArrayList<String>();
		for(Curso c : cursosComentables)
    		cursos.add(c.obtenerNombre());
    }
    
    public void enviarComentario(View view)
    {}

}
