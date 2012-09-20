package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class ActividadConfiguracion extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_configuracion);
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
        	EditText t = (EditText) findViewById(R.id.editando_nombre_ramo);
        	t.setText(extras.getString("nombreDelRamo"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_configuracion, menu);
        return true;
    }
}
