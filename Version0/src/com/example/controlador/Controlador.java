package com.example.controlador;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.data.*;

public class Controlador  //NOTA: ESCRIBIR LOS METODOS NECESARIOS PARA EL CONTROLADOR(NO OLVIDAR QUE EL CONTROLADOR ES COMO UNA CLASE BIBLIOTECA NADA MAS)
{
	static public Curso crearNuevoCurso(Context context, String nombre) //CUANDO ESTO CRESCA NO OLVIDAR AGREGAR ACA NUEVAS CARACTERISTICAS
	{
		AdapterCursos db = new AdapterCursos(context);
		db.open();        
	    String id = db.insertRecordCURSOS(nombre,"0","none")+"";   //por defecto los cursos creados son comentables, pero no tienen un idMaster  
	    db.close();
	    
	   return new Curso(context, id);
	}
	
	/* HACER ESTO*/
	static public Modulo crearNuevoModulo(Context context, Calendar inicio, Calendar fin, String nombre, String idCurso)
	{
		AdapterCursos db = new AdapterCursos(context);
		String stringInicio = agregarCeros(4,inicio.get(Calendar.YEAR))+"-"+agregarCeros(2,inicio.get(Calendar.MONTH))+"-"+agregarCeros(2,inicio.get(Calendar.DATE))+" "+agregarCeros(2,inicio.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,inicio.get(Calendar.MINUTE))+":"+agregarCeros(2,inicio.get(Calendar.SECOND));
		String stringFin = agregarCeros(4,fin.get(Calendar.YEAR))+"-"+agregarCeros(2,fin.get(Calendar.MONTH))+"-"+agregarCeros(2,fin.get(Calendar.DATE))+" "+agregarCeros(2,fin.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,fin.get(Calendar.MINUTE))+":"+agregarCeros(2,fin.get(Calendar.SECOND));
		db.open();        
	    String id = db.insertRecordHORARIOS(stringInicio, stringFin, nombre, idCurso) +"";        
	    db.close();
		return new Modulo(context, id);
	} 
	
	static public ArrayList<Curso> obtenerCursos(Context context)
	{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsCURSOS();
		
		
		if (c.moveToFirst())
        {
            do {
            	String idramo = c.getString(0);
            	Curso curso = new Curso(context,idramo);
                cursos.add(curso);
            } while (c.moveToNext());
        }
		db.close();
        return cursos;
	}
	
	static public ArrayList<Curso> obtenerCursosComentables(Context context)
	{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsCURSOS();
		
		
		if (c.moveToFirst())
        {
            do {
            	String idramo = c.getString(0);
            	Curso curso = new Curso(context,idramo);
            	if(curso.obtenerComentable())
            		cursos.add(curso);
            } while (c.moveToNext());
        }
		db.close();
        return cursos;
		
	}
	
	static public ArrayList<Modulo> obtenerModulos(Context context)
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsHORARIOS();
		
		if (c.moveToFirst())
		{
		     do {
		         String idmodulo = c.getString(0);
		         
		         Modulo modulo = new Modulo(context,idmodulo);
		         modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		 db.close();
				
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerModulosSegunDia(Context context, int dia) //INEFICIENTE!!!!
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsHORARIOS();
		
		if (c.moveToFirst())
		{
		     do {
		         String idmodulo = c.getString(0);
		         Modulo modulo = new Modulo(context,idmodulo);
		         if (modulo.obtenerInicio().get(Calendar.DAY_OF_WEEK) == dia)
		        	modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		 db.close();
				
		return modulos;
	}
	
	
	
	
	private static String agregarCeros(int n, int i) {
		// TODO Auto-generated method stub
		String numero = i+"";
		if(numero.length()<n)
		{
			for(int j =0; j<(n-numero.length());++j)
			{
				numero = "0"+numero;
			}	
		}
		return numero;
	}
}
