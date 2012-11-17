package com.example.version1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdapterProfesores {
    public static final String KEY_ROWID = "id";
    public static final String KEY_ID_USER = "id_user";
    public static final String KEY_NOMBRE = "nombre";
    private static final String TAG = "AdapterProfesores";
    
    private static final String DATABASE_NAME = "Usuario1";
    private static final String DATABASE_TABLE = "Profesores";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
        "create table if not exists Profesores (id integer primary key autoincrement, "
        + "id_user VARCHAR not null, nombre VARCHAR);";
        
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public AdapterProfesores(Context ctx) 
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
    public AdapterProfesores open() throws SQLException 
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
    public long insertRecord(String id_user, String nombre) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID_USER, id_user);
        initialValues.put(KEY_NOMBRE, nombre);
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
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ID_USER,KEY_NOMBRE}, null, null, null, null, null);
    }

    //---retrieves a psarticular record---
    public Cursor getRecord(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,KEY_ID_USER,KEY_NOMBRE}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String id_user, String nombre) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_ID_USER, id_user);
        args.put(KEY_NOMBRE, nombre);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
