package com.example.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import android.content.Context;
import android.database.Cursor;

import com.example.data.*;
//PREGUNTA PROPUESTA PARA ARIEL.... DEBO ACA PONER METODOS ESPECIALIZADOS COMO POR EJEMPLO LA OBTENCION DEL DIA?

public class Modulo

{
	private String id;
	private String idMaster;
	private String idCurso;
	private String nombre;
	private String diaDeLaSemana;
	private Calendar inicio;
	private Calendar fin;
	
	
	public Modulo(Context context,String id)
	{
		this.id = id;
		///POR DEFECTO EL MODULO SE CARGA DESDE LA DB
		cargarModuloDesdeDB(context);  //ESTO IMPLICA QUE CADA MODULO HAR� UNA QUERY...(EFICIENCIA?)
								//ADEMAS NO OLVIDAR QUE TODO ESTO NECESITA UN CONTEXTO,
		///POR DEFECTO EL MODULO SE CARGA DESDE LA DB
	}
	
	

	



	public void cargarModuloDesdeDB(Context context) 
	{
		/*
		///CARGADOR POR DEFECTO
		idCurso = "ID DE CURSO ASOCIADO POR DEFCTO";
		nombre = "MODULO"+id;
		inicio = Calendar.getInstance();
		
		fin = (Calendar) inicio.clone();
		fin.add(Calendar.HOUR, 1);
		///CARGADOR POR DEFECTO
		*/	
		
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
		this.idMaster = c.getString(1);
		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		Date a = new Date();
		inicio = new GregorianCalendar();
		setNombre(c.getString(6));
        setDiaDeLaSemana(c.getString(3));
		try
		{
			a = formato.parse(c.getString(4));
			inicio.setTime(a);
		}catch(ParseException e){}
		
		
		a = new Date();
		fin = new GregorianCalendar();
		
		try
		{
			a = formato.parse(c.getString(5));
			fin.setTime(a);
		}catch(ParseException e){}
		
		idCurso = c.getString(2);	
		db.close();	
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		
		 
	}
	
	






			//METODOS DE OBTENCION
			public String obtenerId()
			{
				return id;
			}
			
			public String obtenerIdMaster()
			{
				return idMaster;
			}
			
			public String obtenerIdCurso()
			{
				return idCurso;
			}
			
			public String obtenerNombre()
			{
				return nombre;
			}
			
			public String obtenerDiaDeLaSemana()
			{
				return diaDeLaSemana;
			}
			
			public String obtenerNombreDiaDeLaSemana() 
			{
				if (diaDeLaSemana.equals("2")){
					return "Lunes";
				}
				else if (diaDeLaSemana.equals("3")){
					return "Martes";
					
				}
				else if (diaDeLaSemana.equals("4")){
					return "Mi�rcoles";
					
				}
				else if (diaDeLaSemana.equals("5")){
					return "Jueves";
				}
				else if (diaDeLaSemana.equals("6")){
					return "Viernes";
					
				}
				else if (diaDeLaSemana.equals("7")){
					return "S�bado";
					
				}
				else if (diaDeLaSemana.equals("1")){
					return "Domingo";
					
				}
				else {
					return "DIA "+diaDeLaSemana+" ";
				}
			}
			
			public Calendar obtenerInicio()
			{
				return (Calendar) inicio.clone();
			}
			
			public Calendar obtenerFin()
			{
				return (Calendar) fin.clone();
			}
			
			public String obtenerStringInicio() {
				return  agregarCeros(2,inicio.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,inicio.get(Calendar.MINUTE));
			}
			
			public String obtenerStringFin() {
				return agregarCeros(2,fin.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,fin.get(Calendar.MINUTE));
			}
		//METODOS DE OBTENCION
		
		//METODOS DE SETEO DEL OBJETO(NO DB)
			private void setDiaDeLaSemana(String diaDeLaSemana) 
			{
				this.diaDeLaSemana = diaDeLaSemana;				
			}

			public void setId(String id)
			{
				this.id = id;
			}
			
			public void setIdMaster(String idMaster)
			{
				this.idMaster = idMaster;
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
			public boolean estableceIdCurso(Context context,String idCurso) //DEPRECATED?
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				if(db.updateRecordHORARIOS(Long.parseLong(idCurso),c.getString(1), c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6)) )
				{	
					setIdCurso(idCurso);
					db.close();
					return true;
				}
				db.close();
				return false;
				
			}
			
			public boolean estableceIdMaster(Context context, String idMaster)
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				if(db.updateRecordHORARIOS(Long.parseLong(this.id),idMaster, c.getString(2), c.getString(3),c.getString(4),c.getString(5),c.getString(6)) )
				{	
					setIdMaster(idCurso);
					db.close();
					return true;
				}
				db.close();
				return false;
			}
			
			public boolean establecerNombre(Context context,String nuevoNombre)//NOMBRE O UBICACION EN EL CASO DE LA DB
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				if(db.updateRecordHORARIOS(Long.parseLong(this.id),c.getString(1), c.getString(2),c.getString(3), c.getString(4), c.getString(5),nuevoNombre) )
				{	
					setNombre(nuevoNombre);
					db.close();
					return true;
				}
				db.close();
				return false;
			}
			
			public boolean establecerInicio(Context context, Calendar inicio)
			{
				
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				String stringInicio = agregarCeros(2,inicio.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,inicio.get(Calendar.MINUTE));
				
				if(db.updateRecordHORARIOS(Long.parseLong(this.id),c.getString(1),c.getString(2), c.getString(3),stringInicio, c.getString(5), c.getString(6)))
				{	
					setInicio(inicio);
					db.close();
					return true;
				}
				db.close();
				return false;
			}
			
			public boolean establecerFin(Context context,Calendar fin)
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				String stringFin = agregarCeros(2,fin.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,fin.get(Calendar.MINUTE));
				
				if(db.updateRecordHORARIOS(Long.parseLong(this.id),c.getString(1),c.getString(2), c.getString(3), c.getString(4), stringFin, c.getString(6)))
				{	
					setFin(fin);
					db.close();
					return true;
				}
				db.close();
				return false;
			}
			
			public boolean establecerDiaDeLaSemana(Context context, int dia) //RELLENAR
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				Cursor c = db.getRecordHORARIOS(Long.parseLong(this.id));
				
				if(db.updateRecordHORARIOS(Long.parseLong(this.id),c.getString(1),c.getString(2),dia+"", c.getString(4), c.getString(5), c.getString(6)))
				{	
					setDiaDeLaSemana(dia+"");
					db.close();
					return true;
				}
				db.close();
				return false;
			}
			
			public boolean borrarModulo(Context context) //DEPRECATED ???(DEBERIA IR EN EL CONTROLADOR?)
			{
				AdapterCursos db = new AdapterCursos(context);
				db.open();
				if(db.deleteContactHORARIOS(Long.parseLong(this.id)))
				{	
					db.close();
					return true;
				}
				db.close();
				return false;
			}
		// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
			
			private static String agregarCeros(int n, int i) {
				// TODO Auto-generated method stub
				String numero = i+"";
				int longitud = n-numero.length();
				if(numero.length()<n)
				{
					for(int j =0; j<longitud;++j)
					{
						numero = "0"+numero;
					}	
				}
				
				return numero;
			}







			

}