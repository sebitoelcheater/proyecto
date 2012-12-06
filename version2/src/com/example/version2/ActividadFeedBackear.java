package com.example.version2;

import java.util.ArrayList;
import java.util.List;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;
import com.example.controlador.Modulo;
import com.example.version2.ActividadEdicionRamo.MiModuloEditandoArrayAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

public class ActividadFeedBackear extends Activity {

	public class MiModuloFeedBackeandoArrayAdapter extends ArrayAdapter<Modulo> {

		  private List<Modulo> modulosPorFeedBackear;
		   
		  public MiModuloFeedBackeandoArrayAdapter(Context context, int textViewResourceId, List<Modulo> listaModulos) {
				super(context, textViewResourceId, listaModulos);
				this.modulosPorFeedBackear = listaModulos;
			}
		

		  public void agregarModulo(Modulo modulo) {
	            modulosPorFeedBackear.add(modulo);
	        }

	        public void clear() {
	            modulosPorFeedBackear.clear();
	        }
		  @Override
		  public View getView(final int position, View convertView, ViewGroup parent) {
			  	LayoutInflater inflater=getLayoutInflater();
			  	
			  	View fila=inflater.inflate(R.layout.feedbackeo, parent, false);
			  	
			  	TextView nombre = (TextView)fila.findViewById(R.id.textView1);
				TextView fecha =(TextView)fila.findViewById(R.id.textView2);
				Chronometer cronometro=(Chronometer)fila.findViewById(R.id.chronometer1);
				try{
				nombre.setText(new Curso(convertView.getContext(),modulosPorFeedBackear.get(position).obtenerIdCurso()).obtenerNombre());
				}catch(Exception e){System.out.println("JAJA");}
				fecha.setText(modulosPorFeedBackear.get(position).obtenerNombreDiaDeLaSemana()+" "+modulosPorFeedBackear.get(position).obtenerStringInicio()+" - "+modulosPorFeedBackear.get(position).obtenerStringFin());
				
				
				//ACA VA EL MANEJO DEL CRONOMETRO
				//
				//ACA DEBERIAN IR LOS LISTENER
				
			  return fila;
		  }
		}
	
	
	
	
	private MiModuloFeedBackeandoArrayAdapter adaptador;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_feed_backear);
        
        ListView lista = (ListView)findViewById(R.id.listView1);
        
        
        lista.setAdapter(new MiModuloFeedBackeandoArrayAdapter(this, R.layout.item_modulo_editando, Controlador.obtenerModulos(this)));
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_feed_backear, menu);
        return true;
    }
}
