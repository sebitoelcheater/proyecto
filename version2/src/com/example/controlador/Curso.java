package com.example.controlador;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.http.NoHttpResponseException;

import android.content.Context;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.Toast;

import com.example.data.*;
import com.example.server.server;
import com.example.server.server.NoExisteCursoException;
import com.example.version2.ActividadRamos;
import com.example.version2.R;

public class Curso 
{
	private String id; //iidC
	private String nombre;
	private String idMaster; //idC
	private String idP; 
	private boolean comentable;
	private String color;
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
		setColor(c.getString(5));
		setNombre(c.getString(3));
		setComentable("1".equals(c.getString(4)));
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
		
		public boolean esEditable()
		{
			return !(esDescargado());
		}
		
		public boolean esDescargado()
		{
			return !(idMaster.equals("0"));
		}
		
		public String obtenerColor()
		{
			return color;
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
		
		private void setColor(String string) {
			// TODO Auto-generated method stub
			color = string;
		}
	//METODOS DE SETEO DEL OBJETO(NO DB)
		
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
		
		public boolean establecerNombre(Context context,String nuevoNombre)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), nuevoNombre, Integer.parseInt(c.getString(4)),c.getString(5)))
			{	
				setNombre(nuevoNombre);
				db.close();
				return true;
			}
			db.close();
			return false;
		}
		
		public boolean actualizar(Context context) throws Exception//REVISAR SI LOS TOAST SIRVEN ACA
		{
			boolean b = true;
			if(esDescargado())
			{
				//ACTUALIZAR UN RAMO
				Curso cursoActualizando = new Curso(context,id);
				ArrayList<Modulo> modulosDelCurso = Controlador.obtenerModulosPorIdCurso(context, id);
				cursoActualizando.borrarCurso(context);
				
				try {
					server s = new server();
					
    				b = s.actualizarCurso(idMaster, context);
    			} 
    			catch(UnknownHostException uhe){
    				//REPONER LOS CURSOS
    				String ii = Controlador.crearNuevoCurso(context, Integer.parseInt(idMaster), Integer.parseInt(idP), nombre, comentable, color).obtenerId();
    				for(Modulo m : modulosDelCurso)
    				{
    					Controlador.crearNuevoModulo(context, Integer.parseInt(m.obtenerIdMaster()), Integer.parseInt(ii), Integer.parseInt(m.obtenerDiaDeLaSemana()), m.obtenerInicio(), m.obtenerFin(), m.obtenerNombre());
    				}
    				throw uhe;
    			}catch(NoHttpResponseException nhre)
    			{
    				String ii = Controlador.crearNuevoCurso(context, Integer.parseInt(idMaster), Integer.parseInt(idP), nombre, comentable, color).obtenerId();
    				for(Modulo m : modulosDelCurso)
    				{
    					Controlador.crearNuevoModulo(context, Integer.parseInt(m.obtenerIdMaster()), Integer.parseInt(ii), Integer.parseInt(m.obtenerDiaDeLaSemana()), m.obtenerInicio(), m.obtenerFin(), m.obtenerNombre());
    				}
    				throw nhre;
    			}
				catch(NoExisteCursoException nece)
    			{
    				//REPONER LOS CURSOS
    				String ii = Controlador.crearNuevoCurso(context, Integer.parseInt(idMaster), Integer.parseInt(idP), nombre, comentable, color).obtenerId();
    				for(Modulo m : modulosDelCurso)
    				{
    					Controlador.crearNuevoModulo(context, Integer.parseInt(m.obtenerIdMaster()), Integer.parseInt(ii), Integer.parseInt(m.obtenerDiaDeLaSemana()), m.obtenerInicio(), m.obtenerFin(), m.obtenerNombre());
    				}
    				throw nece;
    				
    			} 
    			catch (Exception e) {
				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
				
				
			}

			return b;
		}
		
		public boolean establecerIdMaster(Context context, String idMaster)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), idMaster, c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)),c.getString(5)))
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
			String stringComentable = "0";
			if(comentable)
				stringComentable = "1";
			
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), c.getString(3),Integer.parseInt(stringComentable),c.getString(5)))
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
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), idP, c.getString(3), Integer.parseInt(c.getString(4)),c.getString(5)))
			{	
				setIdP(idP);
				db.close();
				return true;
			}
			db.close();
			return false;
		}
		
		public boolean establecerColor(Context context, String color)
		{
			AdapterCursos db = new AdapterCursos(context);
			db.open();
			Cursor c = db.getRecordCURSOS(Long.parseLong(this.id));
			if(db.updateRecordCURSOS(Long.parseLong(this.id), c.getString(1), c.getString(2), c.getString(3), Integer.parseInt(c.getString(4)),color))
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
