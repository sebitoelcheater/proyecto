package com.example.version2;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
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
	 private ArrayList<Curso> cursosComentables;
	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_feedback);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
    	
		cursosComentables = Controlador.obtenerCursosComentables(this); // ACA DEBERIA IR UN METODO LLAMADO OBTENERCURSOSCOMENTABLES... NO OLVIDAR PEDIRMELO!
    	cursos = new ArrayList<String>();
		for(Curso c : cursosComentables)
    		cursos.add(c.obtenerNombre());
    }
    
    public void enviarComentario(View view)
    {
    	//Hola, soy Seba. Capturar spinner
    	Spinner mySpinner = (Spinner)findViewById(R.id.spinner1);
       	Curso ramo = cursosComentables.get(mySpinner.getSelectedItemPosition());
    	//Capturar comentario
    	comentario = (EditText) findViewById(R.id.editText1);
    	//Crear objeto de la clase post y posteriormente ejecutar método comentar
    	server objeto1 = new server();
    	
    	try{
    		objeto1.comentar(view, 1, comentario.getText().toString()); 
    		//objeto1.comentar2(view,1,ramo,Controlador.ultimoModulo(this,ramo),comentario.getText().toString());
    	}catch(Exception e){ Toast.makeText(view.getContext(), "Error :Curso ya no existe o no tienes acceso a Internet", Toast.LENGTH_LONG).show();}	
    	 
    	
    	
    	//Gracias por el feedback
    	Toast toast = Toast.makeText(getApplicationContext(), "Gracias por tu feedback!", Toast.LENGTH_SHORT);
    	toast.show();
    }

}
