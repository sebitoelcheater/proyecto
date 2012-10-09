package com.example.version0;

import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
	
	private String id;
	private String nombre;
	
	
	
	public Curso(Context context,String id)
	{
		this.id = id;
		this.nombre = "";
	}
	
	public Curso(Parcel in)
	{
		readFromParcel(in);
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
	
	public ArrayList<String> getListaDeModuloSegunDia(Context context, int dia) //NO OLVIDAR MODIFICAR CON LA DB//NOOOO DESDE LA LISTA DE OBJETOS MODULOS :)
	{
		// TODO Auto-generated method stub
		ArrayList<String> listaDeModulos = new ArrayList<String>();
		listaDeModulos.add(nombre+": Modulo"+dia);
		return listaDeModulos;
	}
	
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(nombre);
	}
	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		id = in.readString();
		nombre = in.readString();
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
