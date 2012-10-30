package com.example.version0;

import java.util.ArrayList;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controlador.*;
import com.example.data.*;



public class ActividadDatosDelRamo extends Activity implements OnItemClickListener {
	
	public Curso cursoAEditar;
	Integer [] numeros = {1,2,3,4,5,6,7};
	String [] modulos= {"Lunes 10:00-11:30","Miercoles 13:30-14:45","...Agregar"};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Recibe el mensaje del inetent
        Intent intent = getIntent();
        //Aquí recibe la id (como string) del ramo a editar
        String idRamoAEditar = intent.getStringExtra("id");
        
        //Lo siguiente es muuuy poco eficiente. NECESARIO UN Controlador.obtenerCurso(id)
        cursoAEditar = new Curso(this,idRamoAEditar);
       
        String nombreOriginal = cursoAEditar.obtenerNombre();
        
        
        setContentView(R.layout.activity_actividad_datos_del_ramo);
        /*Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        //numeroDeNotas.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,numeros));
        //ListView listaDeModulos = (ListView) findViewById(R.id.listView1);
        //listaDeModulos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,modulos));
        //listaDeModulos.setOnItemClickListener(this);
        //TextView textNombreRamo = (TextView) findViewById(R.id.textNombreRamo);
        textNombreRamo.setText(idRamoAEditar);*/
        
        EditText campoTextoNombre = (EditText) findViewById(R.id.nombreRamoAEditar);

        campoTextoNombre.setText(nombreOriginal);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_datos_del_ramo, menu);
        return true;
    }

    public void guardarCambios(View view)
	{
        EditText campoTextoNombre = (EditText) findViewById(R.id.nombreRamoAEditar);
        String nuevoNombre = campoTextoNombre.getText().toString();
    	String nombreOriginal = cursoAEditar.obtenerNombre();

        
		if (nuevoNombre != nombreOriginal){
			cursoAEditar.establecerNombre(this, nuevoNombre);
			
		}
		  finish();
		  /*
		   * TODO: Hacer que refresque la activity anterior*/
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}