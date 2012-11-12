package com.example.version0;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;
import com.example.controlador.Modulo;



public class ActividadEdicionRamo extends ListActivity implements OnItemClickListener {
	
	public class MiModuloEditandoArrayAdapter extends ArrayAdapter<Modulo> {

		  private List<Modulo> objects;
		   
		  public MiModuloEditandoArrayAdapter(Context context, int textViewResourceId, List<Modulo> listaModulos) {
				super(context, textViewResourceId, listaModulos);
				this.objects = listaModulos;
			}
		


		  @Override
		  public View getView(final int position, View convertView, ViewGroup parent) {
			  	LayoutInflater inflater=getLayoutInflater();
			  	//Recoje la view de la lista
			  	View fila=inflater.inflate(R.layout.item_modulo, parent, false);
			  	//Recoje textview donde va el nombre del ramo
				TextView diaModulo=(TextView)fila.findViewById(R.id.diaModulo);
				TextView horaInicio=(TextView)fila.findViewById(R.id.horaInicio);
				TextView horaFin=(TextView)fila.findViewById(R.id.horaFin);
				//Le pone el nombre al campo de texto del nombre del ramo
				diaModulo.setText(objects.get(position).obtenerNombreDiaDeLaSemana());
				horaInicio.setText(objects.get(position).obtenerStringInicio());
				horaFin.setText(objects.get(position).obtenerStringFin());
				
				
				Button boton_editar = (Button)fila.findViewById(R.id.botonEditarModulo);
				boton_editar.setOnClickListener(new View.OnClickListener() {
		             public void onClick(View v) {
		                 // Perform action on click
		             	Intent intent = new Intent(ActividadEdicionRamo.this,ActividadEdicionRamo.class);
		             	//Envía la id del módulo
		             	intent.putExtra("id", objects.get(position).obtenerId());
		             	startActivity(intent);
		             }
		         });
			  return fila;
		  }
		} 

	
	public Curso cursoAEditar;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Recibe el mensaje del inetent
        Intent intent = getIntent();
        //Aquí recibe la id (como string) del ramo a editar
        String idRamoAEditar = intent.getStringExtra("id");
        
        cursoAEditar = new Curso(this,idRamoAEditar);
       
        String nombreOriginal = cursoAEditar.obtenerNombre();
        ArrayList<Modulo> array_modulos = Controlador.obtenerModulosPorIdCurso(this, idRamoAEditar);

        
        
        setContentView(R.layout.activity_actividad_edicion_ramo);
        /*Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        //numeroDeNotas.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,numeros));
        //ListView listaDeModulos = (ListView) findViewById(R.id.listView1);
        //listaDeModulos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,modulos));
        //listaDeModulos.setOnItemClickListener(this);
        //TextView textNombreRamo = (TextView) findViewById(R.id.textNombreRamo);
        textNombreRamo.setText(idRamoAEditar);*/
        
        EditText campoTextoNombre = (EditText) findViewById(R.id.nombreRamoAEditar);

        campoTextoNombre.setText(nombreOriginal);
        
        setListAdapter(new MiModuloEditandoArrayAdapter(this, R.layout.item_modulo_editando, array_modulos));

        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_datos_del_ramo, menu);
        return true;
    }

    public void guardarCambios(View view)
	{
        EditText campoTextoNombre = (EditText) findViewById(R.id.nombreRamoAEditar);
        String nuevoNombre = campoTextoNombre.getText().toString();
    	String nombreOriginal = cursoAEditar.obtenerNombre();

        
		if (nuevoNombre != nombreOriginal){
			cursoAEditar.establecerNombre(this, nuevoNombre);
			
		}
		setResult(RESULT_OK);
		  finish();
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public class DialogoModulo extends DialogFragment {
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        // Use the Builder class for convenient dialog construction
	        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	     // Get the layout inflater
	        LayoutInflater inflater = getActivity().getLayoutInflater();
	     // Inflate and set the layout for the dialog
	        // Pass null as the parent view because its going in the dialog layout
	        builder.setView(inflater.inflate(R.layout.activity_actividad_datos_modulo, null))	
	               .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
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
	}
	
	
	
}