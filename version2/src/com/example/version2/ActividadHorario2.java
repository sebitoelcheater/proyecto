package com.example.version2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.controlador.*;
import com.example.data.*;
public class ActividadHorario2 extends Activity{ //PONER COLORES :)
	
	int hoy;
	int viendo;
	//String []diasDeLaSemana = {"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
    ArrayList<HashMap<String,String>> array_modulos = new ArrayList<HashMap<String,String>>();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Calendar c = Calendar.getInstance();
        hoy = c.get(Calendar.DAY_OF_WEEK);
        viendo = hoy;
        /*antigua funcionalidad
    	Spinner numeroDeNotas = (Spinner)findViewById(R.id.spinner1);
        numeroDeNotas.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,diasDeLaSemana));
       */
        
        //Esto puede ser un actualizable
        /*ListView modulos = (ListView)findViewById(R.id.listView1);
        modulos.setAdapter(new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1,array_modulos));
        */
        generaModulos();
       // Button ver = (Button)findViewById(R.id.button1);
        //ver.setOnClickListener(this);
       
    }
	 private static final int SHARE = Menu.FIRST+1;
	    
	    
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {

	        super.onCreateOptionsMenu(menu);
	        /*menu.add(Menu.NONE, NEW_ITEM, 1, R.string.menu_newitem).setIcon(
	                android.R.drawable.ic_menu_add);
	        menu.add(Menu.NONE, RESET_ALL, 2, R.string.menu_resetall).setIcon(
	                android.R.drawable.ic_menu_revert);
	        menu.add(Menu.NONE, SHARE, 3, R.string.menu_share).setIcon(
	                android.R.drawable.ic_menu_share);
	        menu.add(Menu.NONE, PREFERENCES, 4, R.string.menu_preferences).setIcon(
	                android.R.drawable.ic_menu_preferences);*/
	        menu.add(Menu.NONE,SHARE,5,"Compartir!").setIcon(R.drawable.ic_twitter);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {

	        switch (item.getItemId()) {

	        /*case NEW_ITEM:
	            Intent call = new Intent(TallyphantActivity.this, ItemEdit.class);
	            startActivityForResult(call, REQUEST_EDITITEM);
	            return true;

	        case RESET_ALL:
	            db.resetAll();
	            remoteNotifyAll();
	            updateItemList();
	            return true;

	        case SHARE:
	            Intent i = new Intent(android.content.Intent.ACTION_SEND);
	            i.setType("text/plain");
	            i.putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject);
	            Vector<DB.Item> items = db.getItems();
	            String msg = "";
	            for (DB.Item titem : items)
	                msg += titem.getFormatted() + "\n";
	            i.putExtra(Intent.EXTRA_TEXT, msg);
	            startActivity(Intent.createChooser(i,
	                    getString(R.string.share_title)));
	            return true;

	        case PREFERENCES:
	            Intent prefs = new Intent(getBaseContext(), Preferences.class);
	            startActivityForResult(prefs, REQUEST_PREFS);
	            return true;*/

	       
	            
	        case SHARE:
	        	 LayoutInflater lis = LayoutInflater.from(this);
	             View view1 = lis.inflate(R.layout.compartir, null);

	             // Fill in the version...
	            
	             Builder p1 = new AlertDialog.Builder(this).setView(view1);
	             final AlertDialog alrt1 = p1.create();
	             alrt1.setIcon(R.drawable.ic_launcher);
	             alrt1.setTitle("COMPARTIR MIS CURSOS");//ACA HACER EL SWICTH SEGUN LA ACTIVIDAD
	             alrt1.setButton(AlertDialog.BUTTON_NEUTRAL,
	                     "COMPARTIR",
	                     new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog,
	                                 int whichButton) {
	                            
	                         }
	                     });
	             alrt1.setButton(AlertDialog.BUTTON_NEGATIVE, "Salir",
	                     new DialogInterface.OnClickListener() {
	                         public void onClick(DialogInterface dialog,
	                                 int whichButton) {
	                         }
	                     });
	             alrt1.show();
	             return true;
	        
	        }
	        return super.onOptionsItemSelected(item);
	    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario, menu);
        return true;
    }*/
/*
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//generaModulos(numeroDeNotas.getSelectedItemPosition());
		generaModulos();
		
	}*/
/*METODO ANTIGUO
	private void generaModulos(int selectedItemPosition) {
		// TODO Auto-generated method stub
		
		ArrayList<Modulo> modulos = Controlador.obtenerModulosSegunDia(this, selectedItemPosition+1);
		this.array_modulos= new ArrayList<String>();
		for(Modulo m : modulos)
			array_modulos.add(m.obtenerNombre());
			
		
	}*/

	private void generaModulos() {
		// TODO Auto-generated method stub
		ListView listaModulos = (ListView)findViewById(R.id.listView1);
		//listaModulos.clearFocus();
		ArrayList<Modulo> modulos;
		TextView titulo = (TextView)findViewById(R.id.textView1);
		if (hoy==viendo)
		{
			modulos = Controlador.obtenerLosSiguientesModulosDelDia(this, Calendar.getInstance(), 5);
			titulo.setText("Siguientes clases(HOY)");
		}
		
		else
		{
			modulos = Controlador.obtenerModulosSegunDia(this, viendo);
			titulo.setText(stringDDS(viendo));
		}
			
		
		this.array_modulos= new ArrayList<HashMap<String,String>>();
		for(Modulo m : modulos) 
		{	
			Curso c = new Curso(this,m.obtenerIdCurso());
			ViewModuloLista l = new ViewModuloLista(this); 
			l.setNombreSala(c.obtenerNombre()+" ("+m.obtenerNombre()+")");
			l.setHora( m.obtenerStringInicio());
			String color = c.obtenerColor();
			l.setColor(Color.rgb(Controlador.getRed(color), Controlador.getGreen(color), Controlador.getBlue(color)));
			listaModulos.addFooterView(l);
		}
		
        
	}
	
	public void verSiguiente(View v)
	{
		if(++viendo>7)
			viendo = 1;
		
		generaModulos();
	}
	
	public void verAnterior(View v)
	{
		if(--viendo<1)
			viendo = 7;
		
		generaModulos();
	}
	
	public void verHoy(View v)
	{
		viendo = hoy;
		generaModulos();
	}
	
	private String stringDDS(int dia)
	{
		if (dia==2){
			return "Lunes";
		}
		else if (dia==3){
			return "Martes";
			
		}
		else if (dia==4){
			return "Miércoles";
			
		}
		else if (dia==5){
			return "Jueves";
		}
		else if (dia==6){
			return "Viernes";
			
		}
		else if (dia ==7){
			return "Sábado";
			
		}
		else if (dia==1){
			return "Domingo";
			
		}
		else {
			return "DIA "+dia+" ";
		}
	}
}