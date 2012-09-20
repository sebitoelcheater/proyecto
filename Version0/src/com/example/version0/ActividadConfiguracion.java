package com.example.version0;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActividadConfiguracion extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_actividad_configuracion);
        
        //Por ahora trabajaremos con un array de Ramos definidos.
        //Pero debería ser una lista con Ramos e ID's
        String[] array_ramos = new String[] {"Ramo1", "Ramo2", "Ramo 3","...Crear Nuevo Ramo"};
        
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