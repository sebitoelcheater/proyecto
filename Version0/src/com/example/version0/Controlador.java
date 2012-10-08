package com.example.version0;


import java.util.ArrayList;

import com.example.version0.AdapterCursos;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;


public class Controlador implements Parcelable //CLASE SE BASa EN EL MISMO CONTEXT PARA TODAS LAS ACTIVITYS....
{

	private ArrayList<String> nombreDeCursos;
	
	
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
		return new ArrayList<String>(nombreDeCursos);
	}
	
	
	
	
	
	
	//METODO EN DESUSO
	public void nuevaActividad(Context context)
	{
		nombreDeCursos = new ArrayList<String>();
		cargarRamos(context);
	}
	//METODO EN DESUSO
	
	
	
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
	}



     
	private void readFromParcel(Parcel in) {
		 
		nombreDeCursos = new ArrayList<String>();
		in.readList(nombreDeCursos,String.class.getClassLoader());
		
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeList(nombreDeCursos);
		
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
