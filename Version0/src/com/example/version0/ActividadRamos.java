package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
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
}
