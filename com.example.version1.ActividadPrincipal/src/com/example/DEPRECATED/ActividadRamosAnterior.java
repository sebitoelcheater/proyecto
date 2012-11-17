package com.example.DEPRECATED;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.controlador.*;
import com.example.data.*;
import com.example.version0.R;
import com.example.version0.R.id;
import com.example.version0.R.layout;
import com.example.version0.R.menu;
import com.example.version1.ActividadDatosDelRamo;
import com.example.version1.nuevoRamo;
public class ActividadRamosAnterior extends ListActivity {

	 @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_actividad_ramos);
        
        ArrayList<Curso> array_cursos = Controlador.obtenerCursos(this);
        ArrayList<String> array_ramos = new ArrayList<String>();
        ArrayList<String> array_idramos = new ArrayList<String>();
        for(Curso c : array_cursos)
        {
        	array_ramos.add(c.obtenerNombre());
        	array_idramos.add(c.obtenerId());
        }	
        
        //Ahora le damos los ids de las views que se deben ir llenando,
        //en este caso la de los nombres de los ramos
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
    			this,
    			R.layout.lista_ramos,
    			R.id.textoNombreRamo,
    			array_ramos);
        
        
        this.setListAdapter(adapter);
        
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_configuracion, menu);
        return true;
    }
    
    
    protected void onListItemClick (ListView l, View v, int position, long id)
    {
    	Intent intent = new Intent(this,ActividadDatosDelRamo.class);

    	
    	startActivity(intent);
    	
    }
    
    public void configurarnuevoramo(View view)
    {
        Intent i = new Intent(this, nuevoRamo.class );
       
    	startActivity(i);
    }
    
    
   
}



