package com.example.controlador;

import java.util.*;

import android.content.Context;
import android.database.Cursor;

import com.example.data.*;
//PREGUNTA PROPUESTA PARA ARIEL.... DEBO ACA PONER METODOS ESPECIALIZADOS COMO POR EJEMPLO LA OBTENCION DEL DIA?

public class Modulo

{
	private String id;
	private String idCurso;
	private String nombre;
	private Calendar inicio;
	private Calendar fin;
	
	public Modulo(Context context,String id)
	{
		this.id = id;
		///POR DEFECTO EL MODULO SE CARGA DESDE LA DB
		cargarModuloDesdeDB(context);  //ESTO IMPLICA QUE CADA MODULO HARÁ UNA QUERY...(EFICIENCIA?)
								//ADEMAS NO OLVIDAR QUE TODO ESTO NECESITA UN CONTEXTO,
		///POR DEFECTO EL MODULO SE CARGA DESDE LA DB
	}
	
	

	



	public void cargarModuloDesdeDB(Context context) 
	{
		
		///CARGADOR POR DEFECTO
		idCurso = "ID DE CURSO ASOCIADO POR DEFCTO";
		nombre = "MODULO"+id;
		inicio = Calendar.getInstance();
		
		fin = (Calendar) inicio.clone();
		fin.add(Calendar.HOUR, 1);
		///CARGADOR POR DEFECTO
			
		/*
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		AdapterHorarios db = new AdapterHorarios(context);
		Cursor c = db.getRecord(Long.parseLong(this.id));
		c.get
		setNombre(c.getString(1));
        db.close();	
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		 * */ //NOTA : APRENDER A TRABAJAR CON DATOS DATE EN LOS CURSORES.... COMPLETAR LOS DEMAS METODOS QUE REQUIEREN LA DB ///HACIA ABAJO DESDE AQUI Y COMPLETAR EL CONTROLADOR
		 
	}
	
	//METODOS DE OBTENCION
			public String obtenerId()
			{
				return id;
			}
			
			public String obtenerIdCurso()
			{
				return idCurso;
			}
			
			public String obtenerNombre()
			{
				return nombre;
			}
			
			public Calendar obtenerInicio()
			{
				return (Calendar) inicio.clone();
			}
			
			public Calendar obtenerFin()
			{
				return (Calendar) fin.clone();
			}
		//METODOS DE OBTENCION
		
		//METODOS DE SETEO DEL OBJETO(NO DB)
			public void setId(String id)
			{
				this.id = id;
			}
			
			public void setIdCurso(String idCurso)
			{
				this.idCurso = idCurso;
			}
			
			public void setNombre(String nombre)
			{
				this.nombre = nombre;
			}
			
			public void setInicio(Calendar inicio)
			{
				this.inicio = (Calendar) inicio.clone();
			}
			
			public void setFin(Calendar fin)
			{
				this.fin = (Calendar) fin.clone();
			}
		//METODOS DE SETEO DEL OBJETO(NO DB)
		
		// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
			//......to be Continued
			public boolean estableceIdCurso(String idCurso) //DEPRECATED?
			{
				if(true)
				{	
					setIdCurso(idCurso);
					return true;
				}
				
				return false;
			}
			
			public boolean establecerNombre(String nuevoNombre)
			{
				if(true)
				{	
					setNombre(nuevoNombre);
					return true;
				}
				
				return false;
			}
			
			public boolean establecerInicio(Calendar inicio)
			{
				if(true)
				{	
					setInicio(inicio);
					return true;
				}
				
				return false;
			}
			
			public boolean establecerFin(Calendar fin)
			{
				if(true)
				{	
					setFin(fin);
					return true;
				}
				
				return false;
			}
		// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
}