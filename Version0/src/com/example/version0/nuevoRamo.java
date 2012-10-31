package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.controlador.*;
import com.example.data.*;
public class nuevoRamo extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo_ramo);
        
    }
	
	public void crear(View view)
	{
		
		EditText nameTxt = (EditText)findViewById(R.id.editText1);
		Curso c = Controlador.crearNuevoCurso(this, nameTxt.getText().toString());
		//Crea un horario del curso por defecto
		Controlador.crearNuevoModulo(this,Calendar.getInstance().get(Calendar.DAY_OF_WEEK), Calendar.getInstance(), Calendar.getInstance(), c.obtenerNombre(), c.obtenerId());
		  finish();
	}
	
	
}
