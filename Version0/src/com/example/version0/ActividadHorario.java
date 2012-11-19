package com.example.version0;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import com.example.controlador.*;
import com.example.data.*;
public class ActividadHorario extends Activity implements OnClickListener{
	
	
	//String []diasDeLaSemana = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    ArrayList<HashMap<String,String>> array_modulos = new ArrayList<HashMap<String,String>>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);
        /*antigua funcionalidad
    	Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,diasDeLaSemana));
       */
        
        //Esto puede ser un actualizable
        /*ListView modulos = (ListView)findViewById(R.id.listView1);
        modulos.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,array_modulos));
        */
        generaModulos();
        Button ver = (Button)findViewById(R.id.button1);
        ver.setOnClickListener(this);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		//generaModulos(numeroDeNotas.getSelectedItemPosition());
		generaModulos();
		
	}
/*METODO ANTIGUO
	private void generaModulos(int selectedItemPosition) {
		// TODO Auto-generated method stub
		
		ArrayList<Modulo> modulos = Controlador.obtenerModulosSegunDia(this, selectedItemPosition+1);
		this.array_modulos= new ArrayList<String>();
		for(Modulo m : modulos)
			array_modulos.add(m.obtenerNombre());
			
		
	}*/

	private void generaModulos() {
		// TODO Auto-generated method stub
		ArrayList<Modulo> modulos = Controlador.obtenerLosSiguientesModulosDelDia(this, Calendar.getInstance(), 5);
		this.array_modulos= new ArrayList<HashMap<String,String>>();
		for(Modulo m : modulos) 
		{	
			Curso c = new Curso(this,m.obtenerIdCurso());
			HashMap<String,String> mapa = new HashMap<String,String>();
			if(m.obtenerNombre().toUpperCase().equals(c.obtenerNombre().toUpperCase())) //ARREGLAR ESTO!!!!!
				mapa.put("Nombre",m.obtenerNombre());
			else
				mapa.put("Nombre",c.obtenerNombre()+" - "+m.obtenerNombre());
			mapa.put("Hora", m.obtenerStringInicio());
			array_modulos.add(mapa);
		}
		ListView listaModulos = (ListView)findViewById(R.id.listView1);
        listaModulos.setAdapter(new SimpleAdapter(this, array_modulos, android.R.layout.simple_list_item_2, new String[]{"Nombre","Hora"},  new int[] { android.R.id.text1, android.R.id.text2 }));
        
        
	}
}
