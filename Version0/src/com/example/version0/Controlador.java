package com.example.version0;


import java.util.ArrayList;

import com.example.version0.AdapterCursos;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

//AGREGANDO FUNCINALIDADES REALES AL CONTROLADOR....
public class Controlador implements Parcelable //CLASE SE BASa EN EL MISMO CONTEXT PARA TODAS LAS ACTIVITYS....
{

	//private ArrayList<String> nombreDeCursos;
	private ArrayList<Curso> cursos; //NO OLVIDAR: La clase cursos debe ser Parcelable, puse asi se puede pasar de activity en activity
	
	public Controlador(Context context)
	{
		nuevaActividad(context);
	}
	
	public Controlador(Parcel in)
	{
		readFromParcel(in);
	}
	
	
	public ArrayList<String> getNombreCursos()
	{
		//return new ArrayList<String>(nombreDeCursos);
		
		
		ArrayList<String> nombreDeCursos= new ArrayList<String>();
		for(Curso c: cursos)
		{
			nombreDeCursos.add(c.getNombre());
		}
		return nombreDeCursos;  
		
	}
	
	
	
	
	
	
	//METODO EN DESUSO
	public void nuevaActividad(Context context)
	{
		//nombreDeCursos = new ArrayList<String>();
		cursos = new ArrayList<Curso>();
		//PROGRAMAR EL CARGADOR DE CURSOS SEGUN LA BASE DE DATOS
		cargarCursos(context);
		//cargarRamos(context);
	}
	//METODO EN DESUSO
	
	
	
	private void cargarCursos(Context context) {
		// TODO Auto-generated method stub
		AdapterCursos dbramos = new AdapterCursos(context);
		dbramos.open();
        Cursor c = dbramos.getAllRecords();
        
        if (c.moveToFirst())
        {
            do {
                String nombreDeCurso = c.getString(1);
                String idramo = c.getString(0);
                
                Curso curso = new Curso(context,idramo);
                curso.setNombre(nombreDeCurso);
                //Cursor notas = dbnotas.getNotas(idramo);
                //array_notas.add(notas);
                cursos.add(curso);
            } while (c.moveToNext());
        }
        dbramos.close();
	}

	/*
	private void cargarRamos(Context context) {
		// TODO Auto-generated method stub
		AdapterCursos dbramos = new AdapterCursos(context);
		dbramos.open();
        Cursor c = dbramos.getAllRecords();
        
        if (c.moveToFirst())
        {
            do {
                String curso = c.getString(1);
                //String idramo = c.getString(0);
                //Cursor notas = dbnotas.getNotas(idramo);
                //array_notas.add(notas);
                nombreDeCursos.add(curso);
            } while (c.moveToNext());
        }
        dbramos.close();
	}*/



     
	private void readFromParcel(Parcel in) {
		 
		cursos = new ArrayList<Curso>();
		in.readList(cursos,Curso.class.getClassLoader());
		
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeList(cursos);
		
	}
	
    public static final Parcelable.Creator CREATOR =
        	new Parcelable.Creator() {
                public Controlador createFromParcel(Parcel in) {
                    return new Controlador(in);
                }
                public Controlador[] newArray(int size) {
                    return new Controlador[size];
                }
            };

}
