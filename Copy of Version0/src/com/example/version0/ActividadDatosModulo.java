package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ActividadDatosModulo extends Activity {

    String []diasDeLaSemana = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_datos_modulo);
        Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,diasDeLaSemana));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_datos_modulo, menu);
        return true;
    }
}
