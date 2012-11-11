package com.example.version0;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.ListView;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;
import com.example.controlador.Modulo;



public class ActividadDatosDelRamo extends ListActivity implements OnItemClickListener {
	
	public class MiModuloArrayAdapter extends ArrayAdapter<Modulo> {

		  private List<Modulo> objects;
		   
		  public MiModuloArrayAdapter(Context context, int textViewResourceId, List<Modulo> listaModulos) {
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
			  return fila;
		  }
		} 

	
	public Curso cursoAVer;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Recibe el mensaje del inetent
        Intent intent = getIntent();
        //Aquí recibe la id (como string) del ramo a editar
        String idRamoAEditar = intent.getStringExtra("id");
        
        cursoAVer = new Curso(this,idRamoAEditar);
       
        String nombreOriginal = cursoAVer.obtenerNombre();
        ArrayList<Modulo> array_modulos = Controlador.obtenerModulosPorIdCurso(this, idRamoAEditar);

        
        
        setContentView(R.layout.activity_actividad_datos_del_ramo);
        /*Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        //numeroDeNotas.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,numeros));
        //ListView listaDeModulos = (ListView) findViewById(R.id.listView1);
        //listaDeModulos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,modulos));
        //listaDeModulos.setOnItemClickListener(this);
        //TextView textNombreRamo = (TextView) findViewById(R.id.textNombreRamo);
        textNombreRamo.setText(idRamoAEditar);*/
        
        TextView campoTextoNombre = (TextView) findViewById(R.id.nombreRamoAVer);

        campoTextoNombre.setText(nombreOriginal);
        
        setListAdapter(new MiModuloArrayAdapter(this, R.layout.item_modulo, array_modulos));

        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_datos_del_ramo, menu);
        return true;
    }

   

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	public void editarRamo(View view)
    {
		Intent intent = new Intent(this,ActividadEdicionRamo.class);
	 	intent.putExtra("id", cursoAVer.obtenerId());
	 	startActivity(intent);
    }
	
}