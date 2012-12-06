package com.example.version2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.example.controlador.Controlador;
import com.example.controlador.Curso;
import com.example.controlador.Modulo;
import com.example.version2.ActividadEdicionRamo.MiModuloEditandoArrayAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ListView;
import android.widget.TextView;

public class ActividadFeedBackear extends Activity implements OnItemClickListener{

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
			  	int horas = 3;
			  	LayoutInflater inflater=getLayoutInflater();
			  	
			  	View fila=inflater.inflate(R.layout.feedbackeo, parent, false);
			  	
			  	TextView nombre = (TextView)fila.findViewById(R.id.textView1);
				TextView fecha =(TextView)fila.findViewById(R.id.textView2);
				TextView deadLine = (TextView)fila.findViewById(R.id.textView3);
				//Chronometer cronometro=(Chronometer)fila.findViewById(R.id.chronometer1);
				try{
					String nombreCurso = new Curso(convertView.getContext(),modulosPorFeedBackear.get(position).obtenerIdCurso()).obtenerNombre();
				
					nombre.setText(nombreCurso);
					
					Modulo m = modulosPorFeedBackear.get(position);
					Calendar ahora = Calendar.getInstance();
					Calendar fin = m.obtenerFin();
					
					Calendar clon = (Calendar) ahora.clone();
					
					clon.set(Calendar.HOUR_OF_DAY, fin.get(Calendar.HOUR_OF_DAY)+horas);
					clon.set(Calendar.MINUTE, fin.get(Calendar.MINUTE));
					
					
					//
					System.out.println("CLON HORA : " + clon.get(Calendar.HOUR_OF_DAY));
					System.out.println("CLON MINUTO : " + clon.get(Calendar.MINUTE));
					System.out.println("AHORA HORA : " + ahora.get(Calendar.HOUR_OF_DAY));
					System.out.println("AHORA MINUTO : " + ahora.get(Calendar.MINUTE));
					//
					
					//lo que me falta es ahora-clon en milisegundos
					long diferencia =clon.getTimeInMillis()-ahora.getTimeInMillis();
					System.out.println("DIFERENCIA : "+diferencia);
					Calendar cDiferencia = (Calendar) clon.clone();
					
					cDiferencia.setTimeInMillis(diferencia);
					String Tempo =agregarCeros(2,cDiferencia.get(Calendar.HOUR_OF_DAY)) +":"+agregarCeros(2,cDiferencia.get(Calendar.MINUTE));
					
					deadLine.setText("Te quedan "+ Tempo + " para FeedBackear");
				}catch(NullPointerException e){}
				
				fecha.setText(modulosPorFeedBackear.get(position).obtenerNombreDiaDeLaSemana()+" "+modulosPorFeedBackear.get(position).obtenerStringInicio()+" - "+modulosPorFeedBackear.get(position).obtenerStringFin());
				
				
				
				//ACA VA EL MANEJO DEL CRONOMETRO
				//cronometro.start();
				
				//ESTO ESTA EN TRANCE MIENTRAS BUSCO UN CRONOMETRO
				//ACA DEBERIAN IR LOS LISTENER
				
			  return fila;
		  }
		  

			private String agregarCeros(int n, int i) {
				// TODO Auto-generated method stub
				String numero = i+"";
				if(numero.length()<n)
				{
					for(int j =0; j<(n-numero.length());++j)
					{
						numero = "0"+numero;
					}	
				}
				return numero;
			}
		}
	
	
	
	
	private MiModuloFeedBackeandoArrayAdapter adaptador;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_feed_backear);
        
        ListView lista = (ListView)findViewById(R.id.listView1);
        
        ArrayList<Modulo> modulosAFeedBackear = Controlador.obtenerLosFeedBackeables(this,Calendar.getInstance());
        
        lista.setAdapter(new MiModuloFeedBackeandoArrayAdapter(this, R.layout.item_modulo_editando, modulosAFeedBackear));
        
        lista.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_feed_backear, menu);
        return true;
    }

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,ActividadFeedback.class);
    	
    	
    	startActivity(intent);
	}
}
