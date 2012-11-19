package com.example.version2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;
import com.example.controlador.Modulo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ActividadHorarioSemanal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario_semanal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
       
        rellenarModulos();
        
        
        
        
    }

    private void rellenarModulos() 
    {
	   	// TODO Auto-generated method stub
    	//LA RELACION ES 60  corresponde a 60 minutos 
    	
    	int enQueVoy = 480;
    	LinearLayout l = (LinearLayout) findViewById(R.id.LinearLayoutLunes);
    	ArrayList<Modulo> modulosDia = Controlador.obtenerModulosSegunDia(this, 2);
		
    	for(Modulo m : modulosDia)
		{
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		//EDITANDO
		
    	enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutMartes);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 3);
		for(Modulo m : modulosDia)
		{
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
			/*
			ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
			HashMap<String,String> item = new HashMap<String,String>();
			  item.put( "line1","line11");
			  item.put( "line2","line22");
			  item.put("line3", "line33");
			  item.put("line4", "line44");
			  list.add( item );
			SimpleAdapter modulo = new SimpleAdapter( 
					this, 
					list,
					R.layout.modulo,
					new String[] { "line1","line2","line3","line4" },
					new int[] { R.id.textView1, R.id.textView2, R.id.textView3, R.id.textView4 } );
			View v = modulo.getVie;*/
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		//EDITANDO
		
		
		
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutMiercoles);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 4);
		for(Modulo m : modulosDia)
		{
			
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutJueves);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 5);
		for(Modulo m : modulosDia)
		{
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutViernes);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 6);
		for(Modulo m : modulosDia)
		{
			
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutSabado);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 7);
		for(Modulo m : modulosDia)
		{
			
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutDomingo);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 1);
		for(Modulo m : modulosDia)
		{
			
			Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,minutos-enQueVoy));
				enQueVoy+= minutos-enQueVoy;
			}
				
			TextView a = new TextView(this);
			a.setText(new Curso(this,m.obtenerIdCurso()).obtenerNombre());
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario_semanal, menu);
        return true;
    }
	/*
	private class UnModulo extends SimpleAdapter
	{

		public UnModulo(Context context, List<? extends Map<String, ?>> data,
				int resource, String[] from, int[] to) {
			super(context, data, resource, from, to);
			// TODO Auto-generated constructor stub
		}}*/
}
