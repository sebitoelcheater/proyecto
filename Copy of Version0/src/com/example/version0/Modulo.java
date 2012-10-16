package com.example.version0;

import java.util.ArrayList;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class Modulo implements Parcelable 

{
	private int dia;
	private String horaInicio;
	private String horaFin;
	private String nombre;
	
	public Modulo(Context context)
	{
		this.nombre = "";
		this.horaInicio="";
		this.horaFin="";
		this.dia = -1;
		nuevaActividad(context);
	}
	
	

	



	public Modulo(Parcel in)
	{
		readFromParcel(in);
	}
	
	public void setDia(int dia)
	{
		this.dia= dia;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public void setHoraInicio(String horaInicio)
	{
		this.horaInicio = horaInicio;
	}
	public void setHoraFin(String horaFin)
	{
		this.horaFin = horaFin;
	}
	
	public int getDia()
	{
		return dia;
	}
	public String getNombre()
	{
		return nombre;
	}
	public String getHoraInicio()
	{
		return horaInicio;
	}
	public String getHoraFin()
	{
		return horaFin;
	}
	
	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		nombre = in.readString();
		horaInicio = in.readString();
		horaFin = in.readString();
		dia = in.readInt();
		
	}

	private void nuevaActividad(Context context) { //¿CONEXION DB?
		// TODO Auto-generated method stub
		
	}

	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(nombre);
		dest.writeString(horaInicio);
		dest.writeString(horaFin);
		dest.writeInt(dia);
	}
	
	public static final Parcelable.Creator CREATOR =
        	new Parcelable.Creator() {
                public Modulo createFromParcel(Parcel in) {
                    return new Modulo(in);
                }
                public Modulo[] newArray(int size) {
                    return new Modulo[size];
                }
            };

}
