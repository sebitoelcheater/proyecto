package com.example.version2;

import java.util.ArrayList;

import com.example.controlador.Controlador;
import com.example.controlador.Modulo;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsoluteLayout;
import android.widget.Button;
import android.widget.LinearLayout;
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
    	LinearLayout l = (LinearLayout) findViewById(R.id.LinearLayoutLunes);
    	ArrayList<Modulo> modulosDia = Controlador.obtenerModulosSegunDia(this, 2);
		for(Modulo m : modulosDia)
		{
			l.addView(new Button(this));
		}
		//EDITANDO
		l = (LinearLayout) findViewById(R.id.LinearLayoutMartes);
		for(int i =0 ; i<20;i++)
		{
			
			Button a = new Button(this);
			a.setText(""+(i+8));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,45));
		}
		/**
		modulosDia = Controlador.obtenerModulosSegunDia(this, 3);
		for(Modulo m : modulosDia)
		{
			LinearLayout l = (LinearLayout) findViewById(R.id.LinearLayoutMartes);
			Button a = new Button(this);
			a.setText(m.obtenerStringInicio());
			l.addView(new TextView(this),new LayoutParams(LayoutParams.FILL_PARENT,180));
			l.addView(a,new LayoutParams(LayoutParams.FILL_PARENT,45));
		}*/
		
		//EDITANDO
		l = (LinearLayout) findViewById(R.id.LinearLayoutMiercoles);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 4);
		for(Modulo m : modulosDia)
		{
			
			l.addView(new Button(this));
		}
		
		
		l = (LinearLayout) findViewById(R.id.LinearLayoutJueves);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 5);
		for(Modulo m : modulosDia)
		{
			
			Button a = new Button(this);
			a.setText(m.obtenerStringInicio());
			l.addView(a);
		}
		
		l = (LinearLayout) findViewById(R.id.LinearLayoutViernes);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 6);
		for(Modulo m : modulosDia)
		{
			
			l.addView(new Button(this));
		}
		
		l = (LinearLayout) findViewById(R.id.LinearLayoutSabado);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 7);
		for(Modulo m : modulosDia)
		{
			
			l.addView(new Button(this));
		}
		
		l = (LinearLayout) findViewById(R.id.LinearLayoutDomingo);
		modulosDia = Controlador.obtenerModulosSegunDia(this, 1);
		for(Modulo m : modulosDia)
		{
			
			l.addView(new Button(this));
		}
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario_semanal, menu);
        return true;
    }
}
