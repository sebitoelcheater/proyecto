package com.example.version0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.ExpandableListActivity;
import android.view.Menu;
import android.widget.SimpleExpandableListAdapter;

public class ActividadNotas extends ExpandableListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_actividad_notas); antiguo xml
        
        //Este Es un poco de codigo para generar la lista expandible
        
        //ESTA ITERACION VA SEGUN EL NUMERO Y NOMBRE DEL LOS RAMOS EXTRAIDOS DE DB
        ArrayList<Map<String,String>> groupData = new ArrayList<Map<String,String>>();
        for(int i=0; i<10; ++i)
        {
        	Map<String,String> mapa = new HashMap<String,String>();
        	groupData.add(mapa);
        	mapa.put("CLAVE", ("Ramo no : "+i));
        }	
        ///ESTA ITERACION VA SEGUN EL NUMERO Y NOMBRE DEL LOS RAMOS EXTRAIDOS DE DB
        
        List<Map<String, String>> children = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (int j = 0; j < 5; j++) {
            Map<String, String> curChildMap = new HashMap<String, String>();
            children.add(curChildMap);
            curChildMap.put("CLAVE", "Hola " + j);
        }
        childData.add(children);
    
        //
        
        setListAdapter(new SimpleExpandableListAdapter(this, groupData, 
        				android.R.layout.simple_expandable_list_item_1, new String[] { "CLAVE" }
        				, new int[] { android.R.id.text1, android.R.id.text2 },childData,
                        android.R.layout.simple_expandable_list_item_2,new String[] { "CLAVE" },
                        new int[] { android.R.id.text1, android.R.id.text2 }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_notas, menu);
        return true;
    }
}
