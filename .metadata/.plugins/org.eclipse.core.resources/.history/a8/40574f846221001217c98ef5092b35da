package com.example.controlador;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.data.*;

public class Curso 
{
	private String id;
	private String nombre;
	private String idMaster;
	private boolean comentable;
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
		db.open();
		Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
		
		setNombre(c.getString(1));
		setComentable("0".equals(c.getString(2)));
		setIdMaster(c.getString(3));
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
		
		public String obtenerIdMaster()
		{
			return idMaster;
		}
		
		public boolean obtenerComentable()
		{
			return comentable;
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
		
		public void setIdMaster(String idMaster)
		{
			this.idMaster = idMaster;
		}
		
		public void setComentable(boolean comentable)
		{
			this.comentable = comentable;
		}
	//METODOS DE SETEO DEL OBJETO(NO DB)
		
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
		
		public boolean establecerNombre(Context context,String nuevoNombre)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), nuevoNombre, c.getString(2), c.getString(3)))
			{	
				setNombre(nuevoNombre);
				db.close();
				return true;
			}
			db.close();
			return false;
		}
		
		public boolean establecerIdMaster(Context context, String idMaster)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), idMaster))
			{	
				setIdMaster(idMaster);
				db.close();
				return true;
			}
			db.close();
			return false;
		}
		
		public boolean establecerComentable(Context context, boolean comentable)
		{
			AdapterCursos db = new AdapterCursos(context);
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			db.open();
			String stringComentable = "1";
			if(comentable)
				stringComentable = "0";
			
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), stringComentable, c.getString(3)))
			{	
				setComentable(comentable);
				db.close();
				return true;
			}
			db.close();
			return false;

		}
		
		public boolean borrarCurso(Context context) //DEPRECATED ???(DEBERIA IR EN EL CONTROLADOR?)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			if(db.deleteContactCURSOS(Long.parseLong(this.id)))
			{	
				db.close();
				return true;
			}
			db.close();
			return false;
		}
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
}
