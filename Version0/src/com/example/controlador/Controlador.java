package com.example.controlador;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;

import com.example.data.*;

public class Controlador 
{
	static public ArrayList<Curso> obtenerCursos(Context context)
	{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		AdapterCursos db = new AdapterCursos(context);
		Cursor c = db.getAllIds();
		
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
	
	static public ArrayList<Modulo> obtenerModulos(Context context)
	{
		ArrayList<Modulo> modulos = new ArrayList<Modulo>();
		AdapterHorarios db = new AdapterHorarios(context);
		Cursor c = db.getAllIds();
		
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
}
