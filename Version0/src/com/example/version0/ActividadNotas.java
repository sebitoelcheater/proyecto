package com.example.version0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

public class ActividadNotas extends ExpandableListActivity{
	
	ArrayList<Map<String,String>> groupData;
    List<List<Map<String, String>>> childData ;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_actividad_notas); antiguo xml
        
        //Este Es un poco de codigo para generar la lista expandible
        
        //ESTA ITERACION VA SEGUN EL NUMERO Y NOMBRE DEL LOS RAMOS EXTRAIDOS DE DB
        configurarListaExpansiva();
           	
        	
        	/*
        	Map<String,String> mapa = new HashMap<String,String>();
        	groupData.add(mapa);
        	mapa.put("CLAVE", ("Ramo no : "+i));
        	
        	List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            
            for (int j = 0; j < 5; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put("CLAVE", "Hola " + j);
            }
            
            childData.add(children);*/
        	
        ///ESTA ITERACION VA SEGUN EL NUMERO Y NOMBRE DEL LOS RAMOS EXTRAIDOS DE DB
        
        
    
        //
        
        
        setListAdapter(new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "CLAVE","CLAVE2" },
                new int[] { android.R.id.text1, android.R.id.text2 },
                childData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] { "CLAVE","CLAVE2" },
                new int[] { android.R.id.text1, android.R.id.text2 }
                )); 
    }

    private void configurarListaExpansiva() //ESTO SE CONFIGURARA SEGUN LOS DATOS DESDE DB
    
    {
		// TODO Auto-generated method stub
    	groupData = new ArrayList<Map<String,String>>();
    	childData = new ArrayList<List<Map<String, String>>>();
    	for(int i=0; i<10; ++i)
         {
         	 Map<String, String> curGroupMap = new HashMap<String, String>();
              groupData.add(curGroupMap);
              curGroupMap.put("CLAVE", "Ramo " + i);
              //curGroupMap.put("CLAVE2", "EsteGrupoEsCLAVE2");
              
              List<Map<String, String>> children = new ArrayList<Map<String, String>>();
              for (int j = 0; j <= i; j++) {
                  Map<String, String> curChildMap = new HashMap<String, String>();
                  children.add(curChildMap);
                  curChildMap.put("CLAVE", "MODULO " + j);
          }
              childData.add(children);
         }
    	
    	//PONER EL sETONCKLICLISTENNER()
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_notas, menu);
        return true;
    }
    
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
    {
    	/*configurarListaExpansiva();
    	setListAdapter(new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { "CLAVE","CLAVE2" },
                new int[] { android.R.id.text1, android.R.id.text2 },
                childData,
                android.R.layout.simple_expandable_list_item_2,
                new String[] { "CLAVE","CLAVE2" },
                new int[] { android.R.id.text1, android.R.id.text2 }
                )); 
    	*/
    	EditText editor = new EditText(null);
    	//Dialog cambio = new AlertDialog(this,5);
    	return true;
    }
}
