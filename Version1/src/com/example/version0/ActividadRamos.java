package com.example.version0;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

//import android.app.Fragment;



//import com.ciarang.tallyphant.DB;
//import com.ciarang.tallyphant.DB;
import com.example.controlador.*;
import com.example.data.*;
import com.example.server.server;
import com.example.version0.R;


public class ActividadRamos extends ListActivity {
    private static final int REQUEST_EDITAR_O_AGREGAR = 0;

	public  String id_curso = null;

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
		
		  public void agregarCurso(Curso curso) {
	            objects.add(curso);
	        }

	        public void clear() {
	            objects.clear();
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
		                 // Para Editar un RAmo
		             	Intent intent = new Intent(ActividadRamos.this,ActividadEdicionRamo.class);
		             	intent.putExtra("id", objects.get(position).obtenerId());
		             	
		             	
		            	/* Este intent envía un "requestCode" que es REQUEST_EDITAR_O_AGREGAR
		            	en el protected void onActivityResult se indica qué es lo que hay que hacer según
		            	los resultados   	
		            	*/
		            	
		             	startActivityForResult(intent, REQUEST_EDITAR_O_AGREGAR);
		             }		         
				});			
				label.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		                 // Para Ver un Ramo
		             	Intent intent = new Intent(ActividadRamos.this,ActividadDatosDelRamo.class);
		             	intent.putExtra("id", objects.get(position).obtenerId());
		             	startActivity(intent);
		             }
		         
		         });
			  return fila;
		  }
		} 

	
	/*public class DialogoSuscribirCurso extends DialogFragment{
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	     // Get the layout inflater
	        LayoutInflater inflater = getActivity().getLayoutInflater();
	     // Inflate and set the layout for the dialog
	        // Pass null as the parent view because its going in the dialog layout
	        builder.setView(inflater.inflate(R.layout.alerta_suscribir_curso, null))	
	               .setPositiveButton("Suscribir", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // FIRE ZE MISSILES!
	                   }
	               })
	               .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
	                   public void onClick(DialogInterface dialog, int id) {
	                       // User cancelled the dialog
	                   }
	               });
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	}*/
	
	private MiArrayAdapter adaptador;
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
        adaptador = new MiArrayAdapter(this, R.layout.lista_ramos, array_cursos);
        setListAdapter(adaptador);
        /*MiArrayAdapter<Curso> adaptadorCursos = new MiArrayAdapter(this, R.layout.lista_ramos, array_cursos);*/
        
        
        /*setListAdapter(*/   
    }
    
    

	 public void actualizarListaRamos() {
	        ArrayList<Curso> nuevo_array_cursos = Controlador.obtenerCursos(this);
	        adaptador.clear();
	        for(Curso curso : nuevo_array_cursos){
	        	adaptador.agregarCurso(curso);
	        }
	        adaptador.notifyDataSetChanged();
	    }
	        
	 
	 @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_configuracion, menu);
        return true;
    }
       
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case REQUEST_EDITAR_O_AGREGAR:
            if (resultCode == RESULT_OK) {
                //remoteNotifyAll();
                actualizarListaRamos();
                getWindow()
                        .setSoftInputMode(
                                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            }
        }
    }
    public void configurarnuevoramo(View view)
    {
    	
    	Intent i = new Intent(this, nuevoRamo.class );
    	/* Este intent envía un "requestCode" que es REQUEST_EDITAR_O_AGREGAR
    	en el protected void onActivityResult se indica qué es lo que hay que hacer según
    	los resultados   	
    	*/
    	startActivityForResult(i, REQUEST_EDITAR_O_AGREGAR);
    }
    
    public void suscribirCurso(View view)
    {
    	/*FragmentManager fm = getFragmentManager();
    	FragmentManager fragmentManager = getSupportFragmentManager();
    	DialogFragment dialogo = new DialogoSuscribirCurso();
    	dialogo.show(getActivity().getSupportFragmentManager(),"hola");*/
    	
    	//Intent i = new Intent(this, ActividadSuscribirCursoActivity.class );
    	//Intent i = new Intent(this, ActividadNotas.class );
    	//startActivityForResult(i, REQUEST_EDITAR_O_AGREGAR);
    	
    	Bundle bundle = new Bundle();
    	bundle.putString("NOTA", "7");//El valor 7 va segun la base de datos
    	showDialog(1,bundle); //Cuidado con el showdialog....

    	
    }
    
protected Dialog onCreateDialog(int id, Bundle b) {
    	
    	final Dialog d = new Dialog(this);
		d.setContentView(R.layout.dialogo_suscribir_curso);
		d.setTitle("Ingrese id del Curso a Suscribir");
		Button boton_suscribir_curso = (Button) d.findViewById(R.id.botonSuscribirCurso);
		//EditText idCurso = (EditText)findViewById(R.id.idCursoASuscribir2);
		//id_curso = idCurso.getText().toString();
		
		boton_suscribir_curso.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	EditText textoid = (EditText) d.findViewById(R.id.idCursoASuscribir2);
        		id_curso = textoid.getText().toString();

        		server servidor = new server();
        		
        		try {
					servidor.suscribirCurso(id_curso,ActividadRamos.this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		actualizarListaRamos();
        		//String id = idCurso.getText().toString();
        		//id_curso = idCurso.getText().toString();
        		/*ttry {
					servidor.suscribirCurso(id_curso,ActividadRamos.this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		ry {
					servidor.suscribirCurso(id_curso,ActividadRamos.this);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
        		d.dismiss();

            }

		});
             return d;
	}
   
   /* private void showPopUp2() {

    	 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
    	 helpBuilder.setTitle("Pop Up");
    	 helpBuilder.setMessage("This is a Simple Pop Up");
    	 helpBuilder.setPositiveButton("Positive",
    	   new DialogInterface.OnClickListener() {

    	    public void onClick(DialogInterface dialog, int which) {
    	     // Do nothing but close the dialog
    	    }
    	   });

    	 helpBuilder.setNegativeButton("Negative", new DialogInterface.OnClickListener() {

    	  public void onClick(DialogInterface dialog, int which) {
    	   // Do nothing
    	  }
    	 });
    	 
    	 helpBuilder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {

    	  public void onClick(DialogInterface dialog, int which) {
    	   // Do nothing
    	  }
    	 });

    	 // Remember, create doesn't show the dialog
    	 AlertDialog helpDialog = helpBuilder.create();
    	 helpDialog.show();

    	}*/
    
        
        

       
    }
   










