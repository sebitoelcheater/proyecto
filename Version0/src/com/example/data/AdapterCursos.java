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
    public static final String KEY_COMENTABLE = "comentable";
    public static final String KEY_ID_MASTER = "idMaster";
    private static final String TAG = "AdapterCursos";
    
    private static final String DATABASE_NAME= "OrganizadorDB";
    private static final String DATABASE_TABLE = "Cursos";
    private static final int DATABASE_VERSION = 3;

    private static final String DATABASE_CREATE =
        "create table if not exists Cursos (id integer primary key autoincrement, title VARCHAR not null, comentable VARCHAR not null, idMaster VARCHAR not null);";
        
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
    public long insertRecord(String title, String comentable, String id_master) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_COMENTABLE, comentable);
        initialValues.put(KEY_ID_MASTER, id_master);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular record---
    public boolean deleteContact(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves all the records---
    public Cursor getAllRecords() //ARREGLANDO...
    {
    	return db.query(DATABASE_TABLE, null, null, null, null, null, null);
    }
    
    public Cursor getAllIds()
    {
    	return db.query(DATABASE_TABLE, new String[] {KEY_ROWID}, null, null, null, null, null);
    	
    }

    //---retrieves a particular record---
    public Cursor getRecord(long rowId) throws SQLException 
    {
    	Cursor mCursor =
                db.query(DATABASE_TABLE, new String[] {KEY_ROWID,
                KEY_TITLE, KEY_COMENTABLE, KEY_ID_MASTER}, 
                KEY_ROWID + "=" + rowId, null, null, null, null, null);
    	if (mCursor != null) {
    		
    		mCursor.moveToFirst();
               }
        return mCursor;
    }
    

    //---updates a record---
    public boolean updateRecord(long rowId, String title, String comentable, String id_master) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_TITLE, title);
        args.put(KEY_COMENTABLE, comentable);
        args.put(KEY_ID_MASTER, id_master);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
