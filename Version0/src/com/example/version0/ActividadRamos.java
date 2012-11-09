package com.example.version0;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.controlador.*;
import com.example.data.*;
public class ActividadRamos extends ListActivity {
	private ArrayList<Curso> array_ramos;
	
	/*Esta clase es muy necesaria para hacer una "personalización"
	 * de los adapter para las listas.
	 * En este en particular configura todo para que el botón lleve la id del ramo!!!!!
	 */
 	//public static String MENSAJE_EXTRA = "com.example.version0.MESSAGE";

	public class MiArrayAdapter extends ArrayAdapter<Curso> {

		  private List<Curso> objects;
		   
		  public MiArrayAdapter(Context context, int textViewResourceId, List<Curso> listaCursos) {
				super(context, textViewResourceId, listaCursos);
				this.objects = listaCursos;
			}
		


		  @Override
		  public View getView(final int position, View convertView, ViewGroup parent) {
			  	LayoutInflater inflater=getLayoutInflater();
			  	//Recoje la view de la lista
			  	View fila=inflater.inflate(R.layout.lista_ramos, parent, false);
			  	//Recoje textview donde va el nombre del ramo
				TextView label=(TextView)fila.findViewById(R.id.textoNombreRamo);
				//Le pone el nombre al campo de texto del nombre del ramo
				label.setText(objects.get(position).obtenerNombre());
				Button boton_editar = (Button)fila.findViewById(R.id.botonEditarRamo);
				boton_editar.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		                 // Perform action on click
		             	Intent intent = new Intent(ActividadRamos.this,ActividadDatosDelRamo.class);
		             	intent.putExtra("id", objects.get(position).obtenerId());
		             	startActivity(intent);
		             }
		         });
			  return fila;
		  }
		} 

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
        
        /*MiArrayAdapter<Curso> adapter = new MiArrayAdapter<Curso>(
    			this,
    			R.layout.lista_ramos,
    			R.id.textoNombreRamo,
    			array_cursos);
        
        
        this.setListAdapter(adapter);*/
        setListAdapter(new MiArrayAdapter(this, R.layout.lista_ramos, array_cursos));
        /*MiArrayAdapter<Curso> adaptadorCursos = new MiArrayAdapter(this, R.layout.lista_ramos, array_cursos);*/
        
        
        /*setListAdapter(*/   
    }
    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_configuracion, menu);
        return true;
    }
        
    public void configurarnuevoramo(View view)
    {
        Intent i = new Intent(this, nuevoRamo.class );
       
    	startActivity(i);
    }
    
  /*  private void actualizarLista() {
        array_ramos = Controlador.obtenerCursos(this);

        mItemList.notifyDataSetChanged();
    }*/
   
}



















//IMPORTANTE: Soy Esteban y aquí trabajaré hasta que esté listo para no
//entorpecer ActividadRamos Original

//HOLA ESTEBAN... CREO QUE NO ESTAS APROVECHANDO LAS VENJAAS DEL CONTROLADOR... :)
/*package com.example.version0;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.controlador.*;
import com.example.data.*;
public class ActividadRamos2 extends ListActivity {
	private ArrayList<String> cursos;
	private ArrayList<String> ids;
	 public AdapterCursos dbramos = new AdapterCursos(this);
	 
	
	
	
	
	private MySimpleArrayAdapter adapter;
    private ListView listView;

	
	public void clicbotoneditar(OnClickListener onClickListener)
    {
		
		long id = 1; //Aqui se debe recuperar el Id del ramo
        Intent intent = new Intent(this, nuevoRamo.class );
        intent.putExtra("ID_RAMO", id);
        startActivity(intent);
    }*/
	

	/*private class AdaptadorLista extends BaseAdapter{
	    View renderer;
	    List<UnRamo> items; //Hace una lista con los objetos de clase UnRamo

	.class);
    	
    	//Necesario para la comunicación entre Activities
    	intent.putExtra(MENSAJE_EXTRA,true);            // call this one and pass it layoutInflater.inflate(R.layout.my_list_item)
	    

	            // whenever you need to set the list of items just use this method.
	            // call it when you have the data ready and want to display it
	    public void setear(List<UnRamo> items){
	        this.items = items;
	        notifyDataSetChanged();
	    }
	    
	    
	    @Override
	    public int getCount() {
	        return items!=null?items.size():0;
	    }
	    @Override
	    public Object getItem(int position) {
	        return items!=null?items.get(position):null;
	    }
	    @Override
	    public long getItemId(int position) {
	        return items!=null?items.get(position).id:-1;
	    
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if(convertView==null){
	            convertView = renderer;
	        }
	        UnRamo item = items.get(position);
	             // replace those R.ids by the ones inside your custom list_item layout.
	        TextView label = (TextView)convertView.findViewById(R.id.textoNombreRamo);
	        label.setText(item.nombre);
	        Button button = (Button)convertView.findViewById(R.id.botonEditarRamo);
	        button.setOnClickListener((android.view.View.OnClickListener) item.listener);
	        return convertView;
	    }


		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
	
*/
		
	/*
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		       setContentView(R.layout.activity_actividad_ramos);
		       cargarDatos();
				
				
        listView = (ListView)findViewById(R.id.listaRamos);
        
        
        ArrayList<UnRamo> ListaRamos = new ArrayList<UnRamo>();
        for (int i = 0; i < 10; i++) {
        	UnRamo ramo = new UnRamo();
        	ramo.nombre = "hola";
            ramo.id = "2";
            ListaRamos.add(ramo);
        }

    
        
        ArrayList<UnRamo> ListaRamos2 = new ArrayList<UnRamo>();
        AdapterCursos db = new AdapterCursos(this);        
        db.open();
        Cursor c = db.getAllRecordsCURSOS();
        
        if (c.moveToFirst())
        {
            do {
            	UnRamo ramo = new UnRamo(){
                	@Override
                    public void clicEditarRamo(String id) {
                		Intent i = new Intent(Intent.ACTION_VIEW, null, null, nuevoRamo.class);

                         startActivity(i);
                    };
                };
                ramo.nombre = c.getString(1);
                ramo.id = c.getString(0);
                ListaRamos.add(ramo);
            } while (c.moveToNext());
        }
        db.close();
        
        
        ListView lvcurso = (ListView) findViewById(R.id.listaRamos);
        
        lvcurso.setAdapter(new CursosAdapter(ListaRamos));*/

       
        /*MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,R.layout.lista_ramos,ListaRamos);
        
        View header = (View)getLayoutInflater().inflate(R.layout.activity_actividad_ramos, null);
        //listView.addHeaderView(header);
        listView.setAdapter(adapter); ///ESTO ES LO QUE CAUSA ERRORES*/
             
   /* }*/
    
    
    
    

    /*@Override
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
    
private void cargarDatos() {
    	
		
    }
   
}

*/




