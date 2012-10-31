package com.example.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdapterCursos {
    public static final String KEY_ROWID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_INICIO = "inicio";
    public static final String KEY_FIN = "fin";
    public static final String KEY_UBICACION = "ubicacion";
    public static final String KEY_ID_CURSO = "id_curso";
    public static final String KEY_COMENTABLE = "comentable";
    public static final String KEY_ID_MASTER = "idMaster";
    public static final String KEY_DDS = "dds";
    private static final String TAG = "AdapterCursos";
    
    private static final String DATABASE_NAME= "OrganizadorDB";
    private static final String DATABASE_TABLE_CURSOS = "Cursos";
    private static final String DATABASE_TABLE_HORARIOS = "Horarios";
    private static final int DATABASE_VERSION = 7;

    private static final String DATABASE_CREATE_CURSOS =
        "create table if not exists Cursos (id integer primary key autoincrement, title VARCHAR not null, comentable VARCHAR not null, idMaster VARCHAR not null);";
        
    private static final String DATABASE_CREATE_HORARIOS =
            "create table if not exists Horarios (id integer primary key autoincrement, dds integer, "
            + "inicio date, fin date, ubicacion VARCHAR, id_curso integer );";
    
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public AdapterCursos(Context ctx) 
    {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
        
    private static class DatabaseHelper extends SQLiteOpenHelper 
    {
        DatabaseHelper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
        	try {
        		db.execSQL(DATABASE_CREATE_CURSOS);	
        		db.execSQL(DATABASE_CREATE_HORARIOS);
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS Cursos");
            db.execSQL("DROP TABLE IF EXISTS Horarios");
            onCreate(db);
        }
    }    

    //---opens the database---
    public AdapterCursos open() throws SQLException 
    {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBHelper.close();
    }
    
    //---insert a record into the database---
    public long insertRecordCURSOS(String title, String comentable, String id_master) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_COMENTABLE, comentable);
        initialValues.put(KEY_ID_MASTER, id_master);
        return db.insert(DATABASE_TABLE_CURSOS, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContactCURSOS(long rowId) 
    {
        return db.delete(DATABASE_TABLE_CURSOS, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecordsCURSOS() //ARREGLANDO...
    {
    	return db.query(DATABASE_TABLE_CURSOS, null, null, null, null, null, null);
    }
    
    public Cursor getAllIdsCURSOS()
    {
    	return db.query(DATABASE_TABLE_CURSOS, new String[] {KEY_ROWID}, null, null, null, null, null);
    	
    }

    //---retrieves a particular record---
    public Cursor getRecordCURSOS(long rowId) throws SQLException 
    {
    	Cursor mCursor =
                db.query(DATABASE_TABLE_CURSOS, new String[] {KEY_ROWID,
                KEY_TITLE, KEY_COMENTABLE, KEY_ID_MASTER}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
    	if (mCursor != null) {
    		
    		mCursor.moveToFirst();
               }
        return mCursor;
    }
    

    //---updates a record---
    public boolean updateRecordCURSOS(long rowId, String title, String comentable, String id_master) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_COMENTABLE, comentable);
        args.put(KEY_ID_MASTER, id_master);
        return db.update(DATABASE_TABLE_CURSOS, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public long insertRecordHORARIOS(String diaDeLaSemana,String inicio, String fin, String ubicacion, String id_curso) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_DDS, diaDeLaSemana);
        initialValues.put(KEY_INICIO, inicio);
        initialValues.put(KEY_FIN, fin);
        initialValues.put(KEY_UBICACION, ubicacion);
        initialValues.put(KEY_ID_CURSO, id_curso);
        return db.insert(DATABASE_TABLE_HORARIOS, null, initialValues);
    }
    
    public boolean deleteContactHORARIOS(long rowId) 
    {
        return db.delete(DATABASE_TABLE_HORARIOS, KEY_ROWID + "=" + rowId, null) > 0;
    }
    
    public Cursor getAllRecordsHORARIOS() 
    {
        return db.query(DATABASE_TABLE_HORARIOS, null, null, null, null, null, null);
    }
    
    public Cursor getAllIdsHORARIOS()
    {
    	 return db.query(DATABASE_TABLE_HORARIOS, new String[] {KEY_ROWID}, null, null, null, null, null);
    }
    
    public Cursor getModulosSiguientesHORARIOS(String diaDeLaSemana,String hora, String limit) {
		// TODO Auto-generated method stub
    	return db.query(DATABASE_TABLE_HORARIOS, null, "("+KEY_DDS+"="+diaDeLaSemana+" and " +KEY_INICIO+">="+hora+")"+" or " + KEY_DDS+">="+diaDeLaSemana, null, null, null, KEY_DDS+","+KEY_INICIO,limit);
	}
    public Cursor getModulosSiguientesDelDiaHORARIOS(String diaDeLaSemana,String hora,String limit)
    {
    	return db.query(DATABASE_TABLE_HORARIOS, null, "("+KEY_DDS+"="+diaDeLaSemana+" and " +KEY_INICIO+">="+hora+")", null, null, null, KEY_INICIO,limit);
    }
    
    public Cursor getModulosInicioEntreHORARIOS(String diaDeLaSemana, String desde,String hasta)
    {
    	return db.query(DATABASE_TABLE_HORARIOS, null,KEY_DDS+"="+diaDeLaSemana+" AND "+KEY_INICIO+">="+desde+" AND "+ KEY_INICIO+ "<="+hasta, null, null, null, KEY_INICIO);
    }
    
    public Cursor getModulosFinEntreHORARIOS(String diaDeLaSemana, String desde,String hasta)
    {
    	return db.query(DATABASE_TABLE_HORARIOS, null, KEY_DDS+"="+diaDeLaSemana+" AND "+KEY_FIN+">="+desde+" AND "+ KEY_FIN+ "<="+hasta, null, null, null, KEY_FIN);
    }
    
    public Cursor getRecordHORARIOS(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE_HORARIOS, new String[] {KEY_ROWID, KEY_DDS,
                KEY_INICIO, KEY_FIN, KEY_UBICACION, KEY_ID_CURSO}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getRecordPorCursoHORARIOS(String idCurso)
    {
    	 Cursor mCursor =
                 db.query(true, DATABASE_TABLE_HORARIOS, new String[] {KEY_ROWID, KEY_DDS,
                 KEY_INICIO, KEY_FIN, KEY_UBICACION, KEY_ID_CURSO}, 
                 KEY_ID_CURSO + "=" + idCurso, null, null, null, null, null);
         if (mCursor != null) {
             mCursor.moveToFirst();
         }
         return mCursor;
    }
    
    public boolean updateRecordHORARIOS(long rowId,String diaDeLaSemana, String inicio, String fin, String ubicacion, String id_curso) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_DDS, diaDeLaSemana);
        args.put(KEY_INICIO, inicio);
        args.put(KEY_FIN, fin);
        args.put(KEY_UBICACION, ubicacion);
        args.put(KEY_ID_CURSO, id_curso);
        return db.update(DATABASE_TABLE_HORARIOS, args, KEY_ROWID + "=" + rowId, null) > 0;
    }

	

}
