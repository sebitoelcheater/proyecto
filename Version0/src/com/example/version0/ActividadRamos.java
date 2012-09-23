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

    public String DisplayRecord(Cursor c)
    {
    	return c.getString(1);  
    } 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        setContentView(R.layout.activity_actividad_ramos);
    
        
        
        //Por ahora trabajaremos con un array de Ramos definidos.
        //Pero debería ser una lista con Ramos e ID's
        //String[] array_ramos = new String[] {"Ramo1", "Ramo2", "Ramo 3"};
        AdapterCursos db = new AdapterCursos(this);
        
        ArrayList array_ramos = new ArrayList();
        
        db.open();
        Cursor c = db.getAllRecords();
        
        if (c.moveToFirst())
        {
            do {          
                String curso = DisplayRecord(c);
                array_ramos.add(curso);
            } while (c.moveToNext());
        }
        db.close();
        
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





/*package com.example.version0;

import android.os.Bundle;
=======
import android.os.Bundle;
import android.app.Activity;
>>>>>>> 4e43b3c81ae3a05e45de4581750413f563ee385f
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActividadRamos extends ListActivity {

    String[] ramosDelUsuario = {"Ramo1","Ramo2","Ramo3","...Crear Nuevo"};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ramosDelUsuario));
        
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_ramos, menu);
        return true;
    }
    
    protected void onListItemClick(ListView l, View v, int position, long id)
    {
    	Intent intent = new Intent(this, ActividadDatosDelRamo.class);
    	
    	startActivity(intent);
    }
}*/

