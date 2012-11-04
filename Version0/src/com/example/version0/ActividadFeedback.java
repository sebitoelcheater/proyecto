package com.example.version0;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controlador.*;
import com.example.data.*;
import com.example.server.server;
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

	 EditText comentario;
	 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_feedback, menu);
        return true;
    }
    private void cargarDatos() {
    	
		ArrayList<Curso> cursosComentables = Controlador.obtenerCursosComentables(this); // ACA DEBERIA IR UN METODO LLAMADO OBTENERCURSOSCOMENTABLES... NO OLVIDAR PEDIRMELO!
    	cursos = new ArrayList<String>();
		for(Curso c : cursosComentables)
    		cursos.add(c.obtenerNombre());
    }
    
    public void enviarComentario(View view)
    {
    	//Hola, soy Seba. Capturar spinner
    	Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);
    	String ramo = mySpinner.getSelectedItem().toString();
    	//Capturar comentario
    	comentario = (EditText) findViewById(R.id.editText1);
    	//Crear objeto de la clase post y posteriormente ejecutar m�todo comentar
    	server objeto1 = new server();
    	objeto1.comentar(view, 1, comentario.getText().toString());
    	
    	//Gracias por el feedback
    	Toast toast = Toast.makeText(getApplicationContext(), "Gracias por tu feedback!", Toast.LENGTH_SHORT);
    	toast.show();
    }

}
