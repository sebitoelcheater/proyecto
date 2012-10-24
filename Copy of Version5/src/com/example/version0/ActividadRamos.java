package com.example.version0;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActividadRamos extends ListActivity {

	private Controlador controlador;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_actividad_ramos);
        Bundle b = getIntent().getExtras();
		controlador = b.getParcelable("CONTROLADOR");
        controlador.nuevaActividad(this); //HACER ESTO EN TODAS LAS ACTIVIDADES?
        //Por ahora trabajaremos con un array de Ramos definidos.
        //Pero debería ser una lista con Ramos e ID's
        //String[] array_ramos = new String[] {"Ramo1", "Ramo2", "Ramo 3"};
        AdapterCursos db = new AdapterCursos(this);
        
        ArrayList array_ramos = controlador.getNombreCursos();
        ArrayList array_idcursos = controlador.getIdCursos();
        
        
        
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

    	intent.putExtra("CONTROLADOR", controlador);	
    	
    	startActivity(intent);
    	
    }
    
    public void configurarnuevoramo(View view)
    {
        Intent i = new Intent(this, nuevoRamo.class );
        i.putExtra("CONTROLADOR", controlador);	
    	
    	startActivity(i);
    }
    
    
   
}


