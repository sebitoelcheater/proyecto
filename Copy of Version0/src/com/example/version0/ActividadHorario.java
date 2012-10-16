package com.example.version0;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class ActividadHorario extends Activity implements OnClickListener{
	
	private Controlador controlador;
	
	String []diasDeLaSemana = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    ArrayList<String> array_modulos = new ArrayList<String>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);
        
        Bundle b = getIntent().getExtras();
		controlador = b.getParcelable("CONTROLADOR");
		controlador.nuevaActividad(this);
		
		Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,diasDeLaSemana));
       
        
        //Esto puede ser un actualizable
        /*ListView modulos = (ListView)findViewById(R.id.listView1);
        modulos.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,array_modulos));
        */
        
        Button ver = (Button)findViewById(R.id.button1);
        ver.setOnClickListener(this);
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario, menu);
        return true;
    }

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
		generaModulos(numeroDeNotas.getSelectedItemPosition());
		
		ListView modulos = (ListView)findViewById(R.id.listView1);
        modulos.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array_modulos));
	}

	private void generaModulos(int selectedItemPosition) {
		// TODO Auto-generated method stub
		array_modulos = controlador.getListaDeModuloSegunDia(this, selectedItemPosition);
	}
}
