package com.example.controlador;

import android.content.Context;
import android.database.Cursor;

import com.example.data.*;

public class Curso 
{
	private String id; //iidC
	private String nombre;
	private String idMaster; //idC
	private String idP; 
	private boolean comentable;
	public Curso(Context context ,String id)
	{
		this.id = id;
		///POR DEFECTO EL CURSO SE CARGA DESDE LA DB
		cargarCursoDesdeDB(context);  //ESTO IMPLICA QUE CADA CURSO HAR� UNA QUERY...(EFICIENCIA?)
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
		
		setNombre(c.getString(3));
		setComentable("0".equals(c.getString(4)));
		setIdMaster(c.getString(1));
		setIdP(c.getString(2));
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
		
		public String obtenerIdP()
		{
			return idP;
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
		
		public void setIdP(String idP)
		{
			this.idP = idP;
		}
	//METODOS DE SETEO DEL OBJETO(NO DB)
		
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
		
		public boolean establecerNombre(Context context,String nuevoNombre)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), nuevoNombre, Integer.parseInt(c.getString(4))))
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
			if(db.updateRecordCURSOS(Long.parseLong(this.id), idMaster, c.getString(2), c.getString(3), Integer.parseInt(c.getString(4))))
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
			
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), c.getString(3),Integer.parseInt(stringComentable)))
			{	
				setComentable(comentable);
				db.close();
				return true;
			}
			db.close();
			return false;

		}
		
		public boolean establecerIdP(Context context, String idP)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), idP, c.getString(3), Integer.parseInt(c.getString(4))))
			{	
				setIdP(idP);
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
				Cursor c = db.getRecordPorCursoHORARIOS(this.id); // ESTO BORRA LOS MODULOS ASOCIADOS AL CURSO
				if (c.moveToFirst())
				{
				     do {
				         String idmodulo = c.getString(0);
				         new Modulo(context,idmodulo).borrarModulo(context);
				         
				       } while (c.moveToNext());
				 }
				db.close();
				return true;
			}
			 db.close();
			db.close();
			return false;
		}
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
}
