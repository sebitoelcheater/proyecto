package com.example.DEPRECATED;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdapterHorarios {
    public static final String KEY_ROWID = "id";
    public static final String KEY_INICIO = "inicio";
    public static final String KEY_FIN = "fin";
    public static final String KEY_UBICACION = "ubicacion";
    public static final String KEY_ID_CURSO = "id_curso";
    private static final String TAG = "AdapterHorarios";
    
    private static final String DATABASE_NAME = "OrganizadorDB";
    private static final String DATABASE_TABLE = "Horarios";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
        "create table if not exists Horarios (id integer primary key autoincrement, "
        + "inicio date, fin date, ubicacion VARCHAR, id_curso integer );";
        
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public AdapterHorarios(Context ctx) 
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
        		db.execSQL(DATABASE_CREATE);	
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }    

    //---opens the database---
    public AdapterHorarios open() throws SQLException 
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
    public long insertRecord(String inicio, String fin, String ubicacion, String id_curso) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_INICIO, inicio);
        initialValues.put(KEY_FIN, fin);
        initialValues.put(KEY_UBICACION, ubicacion);
        initialValues.put(KEY_ID_CURSO, id_curso);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords() 
    {
        return db.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    
    public Cursor getAllIds()
    {
    	 return db.query(DATABASE_TABLE, new String[] {KEY_ROWID}, null, null, null, null, null);
    }
    //---retrieves a psarticular record---
    public Cursor getRecord(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_INICIO, KEY_FIN, KEY_UBICACION, KEY_ID_CURSO}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String inicio, String fin, String ubicacion, String id_curso) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_INICIO, inicio);
        args.put(KEY_FIN, fin);
        args.put(KEY_UBICACION, ubicacion);
        args.put(KEY_ID_CURSO, id_curso);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
