package com.example.version0;

import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
	
	private String id;
	private String nombre;
	private ArrayList<Integer> notas; //CUANDO QUERAMOS PONDERAR COSAS ESTO SERA UNA ESTRUCTURA MAS COMPLEJA QUE UNA LISTA DE INTEGRS
	
	
	
	public Curso(Context context,String id)
	{
		this.id = id;
		this.nombre = "";
		this.notas = new ArrayList<Integer>();
		nuevaActividad(context);
	}
	
	

	public Curso(Parcel in)
	{
		readFromParcel(in);
	}
	
	
	
	public void addNota(int nota) //HACERLO DESDE LA DB
	{
		notas.add(nota);
	}
	
	
	public void changeNota(int position, int nota) //HACERLO DESDE LA DB
	{
		notas.set(position, nota);
	}
	public void setNotas(ArrayList<Integer> notas) //HACERLO DESDE LA DB
	{
		this.notas = notas;
	}
	public void setNombre(String nombre)
	{
		this.nombre= nombre;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	public String getId()
	{
		return id;
	}
	public int getNota(int position)
	{
		return notas.get(position);
	}
	
	public int getPromedio()
	{
		int sum = 0;
		for(Integer nota : notas)
		{
			sum+=nota;
		}
		return (sum/notas.size());
	}
	public ArrayList<Integer> getNotas()
	{
		return new ArrayList<Integer>(notas);
	}
	
	public ArrayList<String> getListaDeModuloSegunDia(Context context, int dia) //NO OLVIDAR MODIFICAR CON LA DB//NOOOO DESDE LA LISTA DE OBJETOS MODULOS :)
	{
		// TODO Auto-generated method stub
		ArrayList<String> listaDeModulos = new ArrayList<String>();
		listaDeModulos.add(nombre+": Modulo"+dia);
		return listaDeModulos;
	}
	
	
	public void nuevaActividad(Context context) 
	{
		notas = new ArrayList<Integer>();
		cargarNotas(context);
		
	}
	
	private void cargarNotas(Context context) //ACA VA LA PARTE DE CONEXION CON LA BASE DE DATOS
	{
		for(int i=0; i<3; i++)
		{
			addNota(i+5);
		}
		
	}



	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(nombre);
		dest.writeList(notas);
	}
	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		id = in.readString();
		nombre = in.readString();
		notas = new ArrayList<Integer>();
		in.readList(notas,Integer.class.getClassLoader());
	}
	
	public static final Parcelable.Creator CREATOR =
        	new Parcelable.Creator() {
                public Curso createFromParcel(Parcel in) {
                    return new Curso(in);
                }
                public Curso[] newArray(int size) {
                    return new Curso[size];
                }
            };



	

}
