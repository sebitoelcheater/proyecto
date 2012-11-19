package com.example.version0;
//Siguiendo el Tutorial de http://www.codeproject.com/Articles/119293/Using-SQLite-Database-with-Android

//En la Version 0 hay que lograr que esta clase sepa:
// *Crear un nuevo Ramo con NOMBRE Y HORARIO
// *Leer el HORARIO (devolver un String)
// *Leer las notas del Ramo
// *Agregar una nueva Nota
/*

package com.example.version0;

import android.database.sqlite.SQLiteOpenHelper;

public class DB extends SQLiteOpenHelper {

static final String NOMBRE_BASE="demoDB";
static final String tablaRamo="Ramos";
static final String colRamoID="ID_Ramos";
static final String colRamoNombre="NombreRamo";
static final String colRamoHorario="Horario";
static final String col="Dept";

///Las originales del tutorial
static final String dbName="demoDB";
static final String employeeTable="Employees";
static final String colID="EmployeeID";
static final String colName="EmployeeName";
static final String colAge="Age";
static final String colDept="Dept";

static final String deptTable="Dept";
static final String colDeptID="DeptID";
static final String colDeptName="DeptName";

static final String viewEmps="ViewEmps";

public DB (Context context) {
	  super(context, dbName, null,33); 
	  }


public void onCreate(SQLiteDatabase db) {
	  // TODO Auto-generated method stub
	  
	  db.execSQL("CREATE TABLE "+deptTable+" ("+colDeptID+ " INTEGER PRIMARY KEY , "+
	    colDeptName+ " TEXT)");
	  
	  db.execSQL("CREATE TABLE "+employeeTable+" ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
	        colName+" TEXT, "+colAge+" Integer, "+colDept+" INTEGER NOT NULL ,FOREIGN KEY ("+colDept+") REFERENCES "+deptTable+" ("+colDeptID+"));");
	  
	  
	  db.execSQL("CREATE TRIGGER fk_empdept_deptid " +
	    " BEFORE INSERT "+
	    " ON "+employeeTable+
	    
	    " FOR EACH ROW BEGIN"+
	    " SELECT CASE WHEN ((SELECT "+colDeptID+" FROM "+deptTable+"" +
	    		" WHERE "+colDeptID+"=new."+colDept+" ) IS NULL)"+
	    " THEN RAISE (ABORT,'Foreign Key Violation') END;"+
	    "  END;");
	  
	  db.execSQL("CREATE VIEW "+viewEmps+
	    " AS SELECT "+employeeTable+"."+colID+" AS _id,"+
	    " "+employeeTable+"."+colName+","+
	    " "+employeeTable+"."+colAge+","+
	    " "+deptTable+"."+colDeptName+""+
	    " FROM "+employeeTable+" JOIN "+deptTable+
	    " ON "+employeeTable+"."+colDept+" ="+deptTable+"."+colDeptID
	    );
	  //Inserts pre-defined departments
	  InsertDepts(db);  
	 }

}*/