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
	private MySimpleArrayAdapter adapter;
    ListView listView;

	
	
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

	            // call this one and pass it layoutInflater.inflate(R.layout.my_list_item)
	    

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
        listView = (ListView)findViewById(R.id.listaRamos);
        
        
        setContentView(R.layout.activity_actividad_ramos);
    
        
        List<UnRamo> ListaRamos = new ArrayList<UnRamo>();
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
       
        MySimpleArrayAdapter adapter = new MySimpleArrayAdapter(this,R.layout.lista_ramos,ListaRamos);
        
        View header = (View)getLayoutInflater().inflate(R.layout.activity_actividad_ramos, null);
        listView.addHeaderView(header);
        listView.setAdapter(adapter); ///ESTO ES LO QUE CAUSA ERRORES
               
        /* try {
        	listView.setAdapter(adapter);
       } catch (Exception e) {
            // This will catch any exception, because they are all descended from Exception
       }
        
        
        
        this.setListAdapter(adapter);
        
        
        
        /*AdaptadorLista adaptador = new AdaptadorLista();
        
        adaptador.setear(ListaRamos);
        setListAdapter(adaptador);
        
        lv = getListView();
        lv.setTextFilterEnabled(true); 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Por ahora trabajaremos con un array de Ramos definidos.
        //Pero debería ser una lista con Ramos e ID's
        //String[] array_ramos = new String[] {"Ramo1", "Ramo2", "Ramo 3"};
        AdapterCursos db = new AdapterCursos(this);
        
        ArrayList array_ramos = new ArrayList();
        ArrayList array_idcursos = new ArrayList();
        
        db.open();
        Cursor c = db.getAllRecords();
        
        if (c.moveToFirst())
        {
            do {          
                String curso = c.getString(1);
                String idcurso = c.getString(0);
                array_ramos.add(curso);
                array_idcursos.add(idcurso);
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
        */
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






