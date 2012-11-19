package com.example.version0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import com.example.controlador.*;
import com.example.data.*;
import com.example.version0.R;
public class Pruebas extends ExpandableListActivity{
	 public AdapterCursos dbramos = new AdapterCursos(this);
	    public AdapterNotas dbnotas = new AdapterNotas(this);
	    ArrayList array_ramos;
		ArrayList array_notas;
		
	    
		ArrayList<Map<String,String>> groupData;
	    List<List<Map<String, String>>> childData ;
	    
	    
	    
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //setContentView(R.layout.activity_actividad_notas); antiguo xml
	        
	        //Este Es un poco de codigo para generar la lista expandible
	        
	        //ESTA ITERACION VA SEGUN EL NUMERO Y NOMBRE DEL LOS RAMOS EXTRAIDOS DE DB
	        array_ramos = new ArrayList();
	        array_notas = new ArrayList();
	        
	        dbramos.open();
	        dbnotas.open();
	        Cursor c = dbramos.getAllRecordsCURSOS();
	        
	        if (c.moveToFirst())
	        {
	            do {
	                String curso = c.getString(1);
	                //String idramo = c.getString(0);
	                //Cursor notas = dbnotas.getNotas(idramo);
	                //array_notas.add(notas);
	                array_ramos.add(curso);
	            } while (c.moveToNext());
	        }
	        dbramos.close();
	        dbnotas.close();
	        
	        
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
	    	for(int i=0; i<array_ramos.size(); ++i)
	         {
	         	 Map<String, String> curGroupMap = new HashMap<String, String>();
	              groupData.add(curGroupMap);
	              curGroupMap.put("CLAVE", (String) array_ramos.get(i));
	              //curGroupMap.put("CLAVE2", "EsteGrupoEsCLAVE2");
	              
	              List<Map<String, String>> children = new ArrayList<Map<String, String>>();
	              for (int j = 0; j <= i; j++) {
	                  Map<String, String> curChildMap = new HashMap<String, String>();
	                  children.add(curChildMap);
	                  curChildMap.put("CLAVE", "7");
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
	    	Bundle b = new Bundle();
	    	b.putString("NOTA", "7");//El valor 7 va segun la base de datos
	    	showDialog(1,b); //Cuidado con el showdialog....
	    	
	    	//Y aca va un metodo que actualice la BASEDEDATOS... y luego la lista expansible
	    	return true;
	    }
	    
	    protected Dialog onCreateDialog(int id, Bundle b) {
	    	
	    	Dialog d = new Dialog(this);
			d.setContentView(R.layout.activity_dialogo);
			d.setTitle("Editor Nota");
			EditText nota = (EditText) d.findViewById(R.id.editText1);
			nota.setText(b.getString("NOTA"));
			Button listo = (Button) d.findViewById(R.id.button1);
			return d;
		}
	}
