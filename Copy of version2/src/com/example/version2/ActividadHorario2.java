package com.example.version2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controlador.*;
import com.example.data.*;
public class ActividadHorario2 extends Activity{ //PONER COLORES :)
	
	int hoy;
	int viendo;
	//String []diasDeLaSemana = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    ArrayList<HashMap<String,String>> array_modulos = new ArrayList<HashMap<String,String>>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Calendar c = Calendar.getInstance();
        hoy = c.get(Calendar.DAY_OF_WEEK);
        viendo = hoy;
        /*antigua funcionalidad
    	Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,diasDeLaSemana));
       */
        
        //Esto puede ser un actualizable
        /*ListView modulos = (ListView)findViewById(R.id.listView1);
        modulos.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,array_modulos));
        */
        generaModulos();
       // Button ver = (Button)findViewById(R.id.button1);
        //ver.setOnClickListener(this);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario, menu);
        return true;
    }
/*
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//generaModulos(numeroDeNotas.getSelectedItemPosition());
		generaModulos();
		
	}*/
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
		ListView listaModulos = (ListView)findViewById(R.id.listView1);
		//listaModulos.clearFocus();
		ArrayList<Modulo> modulos;
		TextView titulo = (TextView)findViewById(R.id.textView1);
		if (hoy==viendo)
		{
			modulos = Controlador.obtenerLosSiguientesModulosDelDia(this, Calendar.getInstance(), 5);
			titulo.setText("Siguientes clases(HOY)");
		}
		
		else
		{
			modulos = Controlador.obtenerModulosSegunDia(this, viendo);
			titulo.setText(stringDDS(viendo));
		}
			
		
		this.array_modulos= new ArrayList<HashMap<String,String>>();
		for(Modulo m : modulos) 
		{	
			Curso c = new Curso(this,m.obtenerIdCurso());
			ViewModuloLista l = new ViewModuloLista(this); 
			l.setNombreSala(c.obtenerNombre()+" ("+m.obtenerNombre()+")");
			l.setHora( m.obtenerStringInicio());
			String color = c.obtenerColor();
			l.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			listaModulos.addFooterView(l);
		}
		
        
	}
	
	public void verSiguiente(View v)
	{
		if(++viendo>7)
			viendo = 1;
		
		generaModulos();
	}
	
	public void verAnterior(View v)
	{
		if(--viendo<1)
			viendo = 7;
		
		generaModulos();
	}
	
	public void verHoy(View v)
	{
		viendo = hoy;
		generaModulos();
	}
	
	private String stringDDS(int dia)
	{
		if (dia==2){
			return "Lunes";
		}
		else if (dia==3){
			return "Martes";
			
		}
		else if (dia==4){
			return "Miercoles";
			
		}
		else if (dia==5){
			return "Jueves";
		}
		else if (dia==6){
			return "Viernes";
			
		}
		else if (dia ==7){
			return "Sabado";
			
		}
		else if (dia==1){
			return "Domingo";
			
		}
		else {
			return "DIA "+dia+" ";
		}
	}
}