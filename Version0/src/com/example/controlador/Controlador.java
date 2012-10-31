package com.example.controlador;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.data.*;

public class Controlador  //NOTA: ESCRIBIR LOS METODOS NECESARIOS PARA EL CONTROLADOR(NO OLVIDAR QUE EL CONTROLADOR ES COMO UNA CLASE BIBLIOTECA NADA MAS)
{
	static public Curso crearNuevoCurso(Context context, String nombre) //CUANDO ESTO CRESCA NO OLVIDAR AGREGAR ACA NUEVAS CARACTERISTICAS
	{
		AdapterCursos db = new AdapterCursos(context);
		db.open();        
	    String id = db.insertRecordCURSOS(nombre,"0","none")+"";   //por defecto los cursos creados son comentables, pero no tienen un idMaster  
	    db.close();
	    
	   return new Curso(context, id);
	}
	
	/* HACER ESTO*/
	static public Curso obtenerCurso(Context context, String id)
	{
		return new Curso(context,id);
	}
	
	static public Modulo crearNuevoModulo(Context context,int diaDeLaSemana, Calendar inicio, Calendar fin, String nombre, String idCurso)
	{
		AdapterCursos db = new AdapterCursos(context);
		String stringInicio = agregarCeros(2,inicio.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,inicio.get(Calendar.MINUTE));
		String stringFin = agregarCeros(2,fin.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,fin.get(Calendar.MINUTE));
		db.open();        
	    String id = db.insertRecordHORARIOS(diaDeLaSemana+"",stringInicio, stringFin, nombre, idCurso) +"";        
	    db.close();
		return new Modulo(context, id);
	} 
	
	
	static public ArrayList<Curso> obtenerCursos(Context context)
	{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsCURSOS();
		
		
		if (c.moveToFirst())
        {
            do {
            	String idramo = c.getString(0);
            	Curso curso = new Curso(context,idramo);
                cursos.add(curso);
            } while (c.moveToNext());
        }
		db.close();
        return cursos;
	}
	

	
	static public ArrayList<Curso> obtenerCursosComentables(Context context)
	{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsCURSOS();
		
		
		if (c.moveToFirst())
        {
            do {
            	String idramo = c.getString(0);
            	Curso curso = new Curso(context,idramo);
            	if(curso.obtenerComentable())
            		cursos.add(curso);
            } while (c.moveToNext());
        }
		db.close();
        return cursos;
		
	}
	
	static public ArrayList<Modulo> obtenerModulos(Context context)
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsHORARIOS();
		
		if (c.moveToFirst())
		{
		     do {
		         String idmodulo = c.getString(0);
		         
		         Modulo modulo = new Modulo(context,idmodulo);
		         modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		 db.close();
				
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerModulosSegunDia(Context context, int dia) //INEFICIENTE!!!!, el dia es de 1 a 7 siendo 1 el domingo
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsHORARIOS();

		if (c.moveToFirst())
		{
		     do {
		    	 String idmodulo = c.getString(0);
		        Modulo modulo = new Modulo(context,idmodulo);
		          if (modulo.obtenerDiaDeLaSemana().equals(dia+""))
		        	modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		 db.close();
				
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerModulosDelDia(Context context, Calendar hoydia) //INEFICIENTE!!!!
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getAllIdsHORARIOS();
		
		if (c.moveToFirst())
		{
		     do {
		         String idmodulo = c.getString(0);
		         Modulo modulo = new Modulo(context,idmodulo);
		         if (modulo.obtenerDiaDeLaSemana().equals(hoydia.get(Calendar.DAY_OF_WEEK)+""))
		        	modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		 db.close();
				
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerLosSiguientesModulos(Context context, Calendar ahora, int largo)
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		String hora = agregarCeros(2,ahora.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,ahora.get(Calendar.MINUTE));
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		
		Cursor c = db.getModulosSiguientesHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"",hora,largo+"");
		if (c.moveToFirst())
		{
		     do {
		         String idmodulo = c.getString(0);
		         Modulo modulo = new Modulo(context,idmodulo);
		         modulos.add(modulo);
		       } while (c.moveToNext());
		 }
		if(modulos.size()<largo)
		{
			while(modulos.size()<largo)
			{
				c = db.getModulosSiguientesHORARIOS(1+"","00:00",largo-modulos.size()+"");
				if (c.moveToFirst())
				{
				     do {
				         String idmodulo = c.getString(0);
				         Modulo modulo = new Modulo(context,idmodulo);
				         modulos.add(modulo);
				       } while (c.moveToNext());
				 }
			}
		}
		db.close();
		
		return modulos;
	}
	
	
	static public ArrayList<Modulo> obtenerModulosSegunIdCurso(Context context, int idCurso)
	{
		
		/*TODO*/
		return null;
	}
	static public ArrayList<Modulo> obtenerLosSiguientesModulosDelDia(Context context, Calendar ahora, int largo)
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		String hora = agregarCeros(2,ahora.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,ahora.get(Calendar.MINUTE));
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		Cursor c = db.getModulosSiguientesDelDiaHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"",hora,largo+"");
		if (c.moveToFirst())
		{
			do {
				String idmodulo = c.getString(0);
				Modulo modulo = new Modulo(context,idmodulo);
				modulos.add(modulo);
			} while (c.moveToNext());
		}
		db.close();
	
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerLosModulosProximosInicio(Context context,Calendar ahora, int minutos) //TERMINAR
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		Calendar despues = ((Calendar)ahora.clone());
		despues.add(Calendar.MINUTE,minutos);
		
		String stringAhora = agregarCeros(2,ahora.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,ahora.get(Calendar.MINUTE));
		String stringDespues = agregarCeros(2,despues.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,despues.get(Calendar.MINUTE));
		
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		if(despues.get(Calendar.DAY_OF_WEEK)== ahora.get(Calendar.DAY_OF_WEEK))
		{
			Cursor c = db.getModulosInicioEntreHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"", stringAhora, stringDespues);
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}
		}	
		else
		{
			Cursor c = db.getModulosInicioEntreHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"", stringAhora, "23:59");
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}
			
			c = db.getModulosInicioEntreHORARIOS(despues.get(Calendar.DAY_OF_WEEK)+"", "00:00", stringDespues);
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}		
					
		}
		db.close();
		
		return modulos;
	}
	
	static public ArrayList<Modulo> obtenerLosModulosAnterioresFin(Context context,Calendar ahora, int minutos) //TERMINAR
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		Calendar antes = ((Calendar)ahora.clone());
		antes.add(Calendar.MINUTE,-minutos);
		
		String stringAhora = agregarCeros(2,ahora.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,ahora.get(Calendar.MINUTE));
		String stringAntes = agregarCeros(2,antes.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,antes.get(Calendar.MINUTE));
		
		AdapterCursos db = new AdapterCursos(context);
		db.open();
		if(antes.get(Calendar.DAY_OF_WEEK)== ahora.get(Calendar.DAY_OF_WEEK))
		{
			Cursor c = db.getModulosFinEntreHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"", stringAntes, stringAhora);
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}
		}	
		else
		{
			Cursor c = db.getModulosFinEntreHORARIOS(ahora.get(Calendar.DAY_OF_WEEK)+"", "00:00", stringAhora);
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}
			
			c = db.getModulosFinEntreHORARIOS(antes.get(Calendar.DAY_OF_WEEK)+"", stringAntes, "23:59");
			if (c.moveToFirst())
			{
				do {
					String idmodulo = c.getString(0);
					Modulo modulo = new Modulo(context,idmodulo);
					modulos.add(modulo);
				} while (c.moveToNext());
			}		
					
		}
		db.close();
		
		return modulos;
	}
	
	
	
	private static String agregarCeros(int n, int i) {
		// TODO Auto-generated method stub
		String numero = i+"";
		if(numero.length()<n)
		{
			for(int j =0; j<(n-numero.length());++j)
			{
				numero = "0"+numero;
			}	
		}
		return numero;
	}
}
