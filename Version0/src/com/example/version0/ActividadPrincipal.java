package com.example.version0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ActividadPrincipal extends Activity {
	public final static String MENSAJE_EXTRA = "com.example.version0.MESSAGE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }
    
    //Metodo ejecutado al presionar el boton de "Envíalo!".
    public void enviarMensaje (View view) {
    	Intent intent = new Intent(this,ActividadQueMuestraMensaje.class);
    	
    	//En este caso se crea un objeto llamado campotexto que se relaciona
    	//con el campo de texto. No se sabe el id, por lo que se recupera, usando
    	//El "nombre clave" edit_message que se le definió en activity_principal.xml
    	EditText campoTexto = (EditText) findViewById(R.id.edit_message);
    	
    	//Recupera el valor del objeto campoTexto, y lo pasa a String
    	String texto = campoTexto.getText().toString();
    	
    	//Necesario para la comunicación entre Activities
    	intent.putExtra(MENSAJE_EXTRA, texto);
    	
    	startActivity(intent);
    }
<<<<<<< HEAD
    public void verRamos (View view) {
=======
    public void verConfiguracion (View view) {
>>>>>>> 4e43b3c81ae3a05e45de4581750413f563ee385f
    	Intent intent = new Intent(this,ActividadRamos.class);
    	
    	//Necesario para la comunicación entre Activities
    	intent.putExtra(MENSAJE_EXTRA,true);
    		
    	
    	startActivity(intent);
    }
    
    public void verHorario (View view) {
    	Intent intent = new Intent(this,ActividadHorario.class);
    	
    	//Necesario para la comunicación entre Activities
    	intent.putExtra(MENSAJE_EXTRA,true);
    		
    	
    	startActivity(intent);
    }
    
    public void verNotas (View view) {
    	Intent intent = new Intent(this,ActividadNotas.class);
    	
    	//Necesario para la comunicación entre Activities
    	intent.putExtra(MENSAJE_EXTRA,true);
    		
    	
    	startActivity(intent);
    }
}
