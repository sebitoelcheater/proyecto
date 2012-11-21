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
import android.graphics.Color;
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
    		Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		//EDITANDO
		
    	enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutMartes);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 3);
		for(Modulo m : modulosDia)
		{
Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		//EDITANDO
		
		
		
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutMiercoles);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 4);
		for(Modulo m : modulosDia)
		{
			
Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutJueves);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 5);
		for(Modulo m : modulosDia)
		{
            Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutViernes);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 6);
		for(Modulo m : modulosDia)
		{
			
            Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutSabado);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 7);
		for(Modulo m : modulosDia)
		{
			
			Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
		enQueVoy = 480;
		l = (LinearLayout) findViewById(R.id.LinearLayoutDomingo);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 1);
		for(Modulo m : modulosDia)
		{
			
			Curso curso = new Curso(this,m.obtenerIdCurso()); 
    		
    		Calendar inicio = m.obtenerInicio();
			int minutos = inicio.get(Calendar.HOUR_OF_DAY)*60+inicio.get(Calendar.MINUTE);
			Calendar fin = m.obtenerFin();
			int duracionEnMinutos = fin.get(Calendar.HOUR_OF_DAY)*60+fin.get(Calendar.MINUTE) - minutos;
			if(enQueVoy<minutos)  //Y si enQueVoy=>minutos(ESTO NO DEBERIA PASAR....)
			{
				l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,2*(minutos-enQueVoy)));
				enQueVoy+= minutos-enQueVoy;
			}
				
			ViewModulo a = new ViewModulo(this);
			a.setHoraInicio(m.obtenerStringInicio());
			a.setHoraFin(m.obtenerStringFin());
			a.setSala(m.obtenerNombre());
			
			a.setNombre(curso.obtenerNombre().substring(0,4));
			String color = curso.obtenerColor();
			a.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,2*duracionEnMinutos));
			enQueVoy+=duracionEnMinutos;
		}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario_semanal, menu);
        return true;
    }
	
}
