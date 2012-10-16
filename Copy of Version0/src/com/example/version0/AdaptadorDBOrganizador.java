package com.example.version0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdaptadorDBOrganizador 
{
	
	/////TABLA CURSOS
	public static final String ID = "id";
	public static final String NOMBRE_TABLA_CURSOS = "Cursos";
	public static final String NOMBRE_CURSO = "nombre";
	/////TABLA CURSOS
	
	//////TABLA HORARIO_SEMANAL
	public static final String NOMBRE_TABLA_HORARIO = "Horario_Semanal";
	public static final String DIA_DE_SEMANA = "dia";
	public static final String INICIO = "inicio"; //DEL TIPO DATE, al igual que fin
	public static final String FIN = "fin";
	public static final String ID_CURSO = "id_curso";
	//////TABLA HORARIO_SEMANAL
	
	/////TABLA NOTAS
	public static final String NOMBRE_TABLA_NOTAS = "Notas";
	public static final String VALOR = "valor";
	///TABLA NOTAS
	
	//DATOS GENERALES DE LA BASE DE DATOS
	public static final String NOMBRE_BD = "Organizador";
	public static final int VERSION_BD = 1;
	public static final String CREAR_BD = "create table if not exists Cursos (id integer primary key autoincrement, nombre text not null);"+
										 "create table if not exists Horario_Semanal (id integer primary key autoincrement, id_curso integer not null, dia integer not null,inicio date, fin date);"+
										 "create table if not exists Notas (id integer primary key autoincrement, id_curso integer not null, valor text not null);"; 
	//DATOS GENERALES DE LA BASE DE DATOS
	
	
	private Context context;
	private DataBaseHelper DBHelper;
	private SQLiteDatabase db;
	
	public AdaptadorDBOrganizador(Context context)
	{
		this.context = context;
		this.DBHelper = new DataBaseHelper(context);
	}
	
	public static class DataBaseHelper extends SQLiteOpenHelper
	{
		DataBaseHelper(Context context)
		{
			super(context,NOMBRE_BD,null,VERSION_BD);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			try {
        		db.execSQL(CREAR_BD);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS Cursos;DROP TABLE IF EXISTS Horario_Semanal;DROP TABLE IF EXISTS Notas;");
			onCreate(db);
		}
		
	}
	
	public AdaptadorDBOrganizador abrir() throws SQLException
	{
		db = DBHelper.getWritableDatabase();
		return this;
	}
	
	public void cerrar()
	{
		DBHelper.close();
	}
	
	public long insertarCurso(String nombre)
	{
		 ContentValues initialValues = new ContentValues();
	        initialValues.put(NOMBRE_CURSO, nombre);
	        return db.insert(NOMBRE_TABLA_CURSOS, null, initialValues);
	}
	
	public boolean borrarCurso(String id)
	{
		return db.delete(NOMBRE_TABLA_CURSOS, ID + "="+id , null) > 0;
	}
	
	public Cursor obtenerTodosLosCursos()
	{
		return db.query(NOMBRE_TABLA_CURSOS, new String[] {ID, NOMBRE_CURSO}, null, null, null, null, null);
	}

	public Cursor obtenerTodasLasNotasDelCurso(String id) {
		// TODO Auto-generated method stub
		return db.query(NOMBRE_TABLA_NOTAS, new String[] {ID,VALOR}, ID+"="+id, null, null, null, null);
	}
	
	public long insertarNota(String idCurso,String nota)
	{
		ContentValues initialValues = new ContentValues();
        initialValues.put(ID_CURSO, idCurso);
		initialValues.put(VALOR, nota);
        return db.insert(NOMBRE_TABLA_NOTAS, null, initialValues);
	}
	
	public boolean borrarNota(String id)
	{
		return db.delete(NOMBRE_TABLA_NOTAS, ID + "="+id , null) > 0;
	}
	
	public boolean actualizarNota(String id,String idCurso,String nota)
	{
		ContentValues initialValues = new ContentValues();
        initialValues.put(ID_CURSO, idCurso);
		initialValues.put(VALOR, nota);
		return db.update(NOMBRE_TABLA_NOTAS, initialValues, ID + "=" + id, null) > 0;
	}
	
	
}
