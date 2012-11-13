package com.example.version0;

import java.util.Calendar;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
		
		server servidor = new server();
		// ESTA LÍNEA ESA FALLANDO
<<<<<<< HEAD
		servidor.suscribirCurso(idCurso.getText().toString());
=======
		EditText temp = (EditText)findViewById(R.id.idRamoASuscribir);
		String idCurso = temp.getText().toString();
		servidor.suscribirCurso(idCurso,this);
>>>>>>> 02fa8de2151e98ec7d3fff6e826cc75f423ffe1d
		setResult(RESULT_OK);
		  finish();
	}
}
