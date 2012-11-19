package com.example.version0;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.controlador.*;
import com.example.data.*;

public class ActividadConfiguracion extends ListActivity {

    public String DisplayRecord(Cursor c)
    {
    	return c.getString(2);  
    } 
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_actividad_configuracion);
        
        //Por ahora trabajaremos con un array de Ramos definidos.
        //Pero debería ser una lista con Ramos e ID's
        
        AdapterCursos db = new AdapterCursos(this);
        
        ArrayList array_ramos = new ArrayList();
        
        db.open();
        Cursor c = db.getAllRecordsCURSOS();
        
        if (c.moveToFirst())
        {
            do {          
                String curso = DisplayRecord(c);
                array_ramos.add(curso);
            } while (c.moveToNext());
        }
        db.close();
        
        //String[] array_ramos = new String[] {"Ramo1", "Ramo2", "Ramo 3","...Crear Nuevo Ramo"};
        
        //Ahora le damos los ids de las views que se deben ir llenando,
        //en este caso la de los nombres de los ramos
        
       this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array_ramos));
        
       // getListView().setTextFilterEnabled(textFilterEnabled)

 
	/*
        
        
         
               
        
        
        ListView listView = (ListView) findViewById(android.R.id.list);

        
        
        
        
       

        // Ahora le asigna los valores a la lista
        listView.setAdapter(adapter); */

        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_configuracion, menu);
        return true;
    }
    
    
    
   
}