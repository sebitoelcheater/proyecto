package com.example.version0;

import java.util.Calendar;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import com.example.server.server;
import com.example.*;

public class ActividadSuscribirCursoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_suscribir_curso);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_suscribir_curso, menu);
        return true;
    }
    
    public void suscribir(View view) throws Exception
	{
		
		EditText idCurso = (EditText)findViewById(R.id.idCursoASuscribir);
		server servidor = new server();
		// ESTA LÍNEA ESA FALLANDO
		servidor.suscribirCurso(idCurso.getText().toString());
		setResult(RESULT_OK);
		  finish();
	}
}
