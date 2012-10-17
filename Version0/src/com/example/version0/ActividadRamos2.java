//IMPORTANTE: Soy Esteban y aquí trabajaré hasta que esté listo para no
//entorpecer ActividadRamos Original

package com.example.version0;

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

public class ActividadRamos2 extends ListActivity {
	private ArrayList<String> cursos;
	private ArrayList<String> ids;
	 public AdapterCursos dbramos = new AdapterCursos(this);
	 private Controlador controlador;
 
	
	
	
	
	private MySimpleArrayAdapter adapter;
    private ListView listView;

	
	public void clicbotoneditar(OnClickListener onClickListener)
    {
		
		long id = 1; //Aqui se debe recuperar el Id del ramo
        Intent intent = new Intent(this, nuevoRamo.class );
        intent.putExtra("ID_RAMO", id);
        startActivity(intent);
    }
	

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
		
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		       setContentView(R.layout.activity_actividad_ramos);
		       Bundle b = getIntent().getExtras();
				controlador = b.getParcelable("CONTROLADOR");
				controlador.nuevaActividad(this);
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
        Cursor c = db.getAllRecords();
        
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
        
        lvcurso.setAdapter(new CursosAdapter(ListaRamos));

       
        /*MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,R.layout.lista_ramos,ListaRamos);
        
        View header = (View)getLayoutInflater().inflate(R.layout.activity_actividad_ramos, null);
        //listView.addHeaderView(header);
        listView.setAdapter(adapter); ///ESTO ES LO QUE CAUSA ERRORES*/
             
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
    
private void cargarDatos() {
    	
		cursos= controlador.getNombreCursos();
    }
   
}






