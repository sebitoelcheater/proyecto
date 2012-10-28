package com.example.controlador;

import android.content.Context;
import android.database.Cursor;

import com.example.data.*;

public class Curso 
{
	private String id;
	private String nombre;
	public Curso(Context context ,String id)
	{
		this.id = id;
		
		///POR DEFECTO EL CURSO SE CARGA DESDE LA DB
		cargarCursoDesdeDB(context);  //ESTO IMPLICA QUE CADA CURSO HARÁ UNA QUERY...(EFICIENCIA?)
							//ADEMAS NO OLVIDAR QUE TODO ESTO NECESITA UN CONTEXTO,
		
		///POR DEFECTO EL CURSO SE CARGA DESDE LA DB
	}
	
	public void cargarCursoDesdeDB(Context context)
	{
		///CARGADOR POR DEFECTO
		///	nombre = "CURSO"+id;
		///CARGADOR POR DEFECTO
		
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		AdapterCursos db = new AdapterCursos(context);
		Cursor c = db.getRecord(Long.parseLong(this.id));
		
		setNombre(c.getString(1));
        db.close();
		///METODO DE OBTENCION DE DATOS DESDE LA DB
	}
	
	//METODOS DE OBTENCION
		public String obtenerId()
		{
			return id;
		}
		
		public String obtenerNombre()
		{
			return nombre;
		}
	//METODOS DE OBTENCION
		
	//METODOS DE SETEO DEL OBJETO(NO DB)
		public void setId(String id)
		{
			this.id = id;
		}
		
		public void setNombre(String nombre)
		{
			this.nombre = nombre;
		}
	//METODOS DE SETEO DEL OBJETO(NO DB)
		
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
		
		public boolean establecerNombre(Context context,String nuevoNombre)
		{
			AdapterCursos db = new AdapterCursos(context);
			if(db.updateRecord(Long.parseLong(this.id), nuevoNombre, "", "", ""))
			{	
				setNombre(nuevoNombre);
				return true;
			}
			
			return false;
		}
		
		public boolean borrarCurso(Context context) //DEPRECATED ???(DEBERIA IR EN EL CONTROLADOR?)
		{
			AdapterCursos db = new AdapterCursos(context);
			if(db.deleteContact(Long.parseLong(this.id)))
			{	
				return true;
			}
			
			return false;
		}
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
}
