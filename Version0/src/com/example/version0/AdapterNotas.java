package com.example.version0;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AdapterNotas {
    public static final String KEY_ROWID = "id";
    public static final String KEY_NOMBRE = "nombre";
    public static final String KEY_DUEDATE = "duedate";
    public static final String KEY_VALOR = "valor";
    public static final String KEY_CLASE = "clase";
    public static final String KEY_ID_USUARIO = "id_usuario";
    private static final String TAG = "AdapterNotas";
    
    private static final String DATABASE_NAME = "Usuario1";
    private static final String DATABASE_TABLE = "Notas";
    private static final int DATABASE_VERSION = 2;

    private static final String DATABASE_CREATE =
        "create table if not exists Notas (id integer primary key autoincrement, "
        + "nombre VARCHAR not null, duedate date, valor VARCHAR, clase VARCHAR, id_usuario integer);";
        
    private final Context context;    

    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public AdapterNotas(Context ctx) 
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
    public AdapterNotas open() throws SQLException 
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
    public long insertRecord(String nombre, String duedate, String valor, String clase) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOMBRE, nombre);
        initialValues.put(KEY_DUEDATE, duedate);
        initialValues.put(KEY_VALOR, valor);
        initialValues.put(KEY_CLASE, clase);
        initialValues.put(KEY_ID_USUARIO, clase);
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
        return db.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_NOMBRE,
                KEY_DUEDATE, KEY_VALOR, KEY_CLASE, KEY_ID_USUARIO}, null, null, null, null, null);
    }

    //---retrieves a psarticular record---
    public Cursor getRecord(long rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_NOMBRE, KEY_DUEDATE, KEY_VALOR, KEY_CLASE, KEY_ID_USUARIO}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    //---updates a record---
    public boolean updateRecord(long rowId, String nombre, String duedate, String valor, String clase, String id_usuario) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NOMBRE, nombre);
        args.put(KEY_DUEDATE, duedate);
        args.put(KEY_VALOR, valor);
        args.put(KEY_CLASE, clase);
        args.put(KEY_ID_USUARIO, id_usuario);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
