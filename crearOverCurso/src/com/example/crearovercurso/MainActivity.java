package com.example.crearovercurso;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    public String funcion1(String a, String b){
    	return a+b;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String hola = funcion1("hola","chao");
        TextView resultado = (TextView)findViewById(R.id.textView1);
        resultado.setText("introduce solo numeros"+hola);
    }
    
    public void lanzar(View view) {
        Intent i = new Intent(this, crearCurso.class );
        startActivity(i);
    }
    public void mostrar(View view){
        Intent i = new Intent(this, mostrarCursos.class );
        startActivity(i);
    }
    
}
