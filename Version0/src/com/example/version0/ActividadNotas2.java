/*package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.controlador.*;
import com.example.data.*;
public class ActividadNotas2 extends Activity implements ExpandableListView.OnChildClickListener, OnClickListener {

    private Controlador controlador;
    
	private ArrayList<String> cursos;
    private ArrayList<ArrayList<ArrayList<String>>> notas;
    public Dialog d;
    public EditText nota;
    public int cursoEditando;
    public int notaEditando;
    
    //public AdapterCursos dbramos = new AdapterCursos(this); AHORA SE USA EL CONTROLADOR
    public AdapterNotas dbnotas = new AdapterNotas(this);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_notas2);
        //Cargando el objeto controlador
        Bundle b = getIntent().getExtras();
		controlador = b.getParcelable("CONTROLADOR");
		controlador.nuevaActividad(this);
        //Cargando el objeto controlador
        ExpandableListView l = (ExpandableListView) findViewById(R.id.expandableListView1);
 
        cargarDatos();
 
        //miExpandableAdapter adaptador = new miExpandableAdapter(this, cursos, notas);
        miExpandableAdapter adaptador = new miExpandableAdapter(this, cursos, notas);
        
        
        l.setAdapter(adaptador);
        l.setOnChildClickListener(this);
    }
	
	public class miExpandableAdapter extends BaseExpandableListAdapter {
		 
    	private ArrayList<String> groups;
 
        private ArrayList<ArrayList<ArrayList<String>>> children;
 
    	private Context context;
 
    	public miExpandableAdapter(Context context, ArrayList<String> groups, ArrayList<ArrayList<ArrayList<String>>> children) {
            this.context = context;
            this.groups = groups;
            this.children = children;
        }
 
 
    	@Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }
 
 
        public ArrayList<String> getChild(int groupPosition, int childPosition) {
            return children.get(groupPosition).get(childPosition);
        }
 
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }
 
 
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) {
 
        	String hijo = (String) ((ArrayList<String>)getChild(groupPosition, childPosition)).get(0);
 
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
            }
            
            TextView notaDelRamo = (TextView) convertView;
            notaDelRamo.setText(hijo);
            
            return convertView;
        }
 
        public int getChildrenCount(int groupPosition) {
            return children.get(groupPosition).size();
        }
 
        public String getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }
 
        public int getGroupCount() {
            return groups.size();
        }
 
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }
 
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
 
        	String group = (String) getGroup(groupPosition);
 
        	if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
            }
        	
        	TextView nombreDelCurso = (TextView) convertView;
            nombreDelCurso.setText(group);
 
            
            return convertView;
        }
 
        public boolean hasStableIds() {
            return true;
        }
 
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }
 
    }

    private void cargarDatos() {
    	
    		cursos= controlador.getNombreCursos();
        	/* AHORA LO HACE EL CONTROLADOR
    		cursos = new ArrayList<String>();
    		
    		dbramos.open();
            dbnotas.open();
            Cursor c = dbramos.getAllRecords();
            
            if (c.moveToFirst())
            {
                do {
                    String curso = c.getString(1);
                    //String idramo = c.getString(0);
                    //Cursor notas = dbnotas.getNotas(idramo);
                    //array_notas.add(notas);
                    cursos.add(curso);
                } while (c.moveToNext());
            }
            dbramos.close();
            dbnotas.close();
           */
 /*           
            notas= new ArrayList<ArrayList<ArrayList<String>>>();
        	for(int i = 0; i< cursos.size();++i)//No olvidar conectar esto con la base de datos
            {
            	notas.add(new ArrayList<ArrayList<String>>());
                
                ArrayList<Integer> notasDelCurso = controlador.getCurso(i).getNotas();
                for(int j = 0; j<notasDelCurso.size();++j)
                {
                	notas.get(i).add(new ArrayList<String>());
                	notas.get(i).get(j).add(notasDelCurso.get(j)+"");
                }
                
            
            	
            }
        	
        
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_notas2, menu);
        return true;
    }

	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
    {
		removeDialog(1);
		cursoEditando = groupPosition;
		notaEditando = childPosition;
		
		Bundle b = new Bundle();
    	TextView nota = (TextView) v;
    	b.putString("NOTA", notas.get(groupPosition).get(childPosition).get(0));//El valor 7 va segun la base de datos
    	showDialog(1,b); //Cuidado con el showdialog....
    	
    	//Y aca va un metodo que actualice la BASEDEDATOS... y luego la lista expansible
    	
    	return true;
    }
    
    protected Dialog onCreateDialog(int id, Bundle b) {
    	
    	d = new Dialog(this);
		d.setContentView(R.layout.activity_dialogo);
		d.setTitle("Editor Nota");
		nota = (EditText) d.findViewById(R.id.editText1);
		nota.setText(b.getString("NOTA"));
		Button listo = (Button) d.findViewById(R.id.button1);
		listo.setOnClickListener(this);
		return d;
	}

	public void onClick(View v) {
		
		try
		{
			controlador.getCurso(cursoEditando).changeNota(notaEditando, Integer.parseInt(nota.getText().toString()));
				//notas.get(cursoEditando).get(notaEditando).remove(0);
				//String temp = nota.getText();
				//notas.get(cursoEditando).get(notaEditando).add(nota.getText().toString());
				cargarDatos();
				actualizar();
				removeDialog(1);
				// TODO Auto-generated method stub
		}catch(NumberFormatException nfe){;}
	}

	private void actualizar() {//En Verdad aqui se deberia actualizar la base de datos y volver a cargar los datos(con los campos de ...Editando :))
		// TODO Auto-generated method stub
		ExpandableListView l = (ExpandableListView) findViewById(R.id.expandableListView1);
		miExpandableAdapter adaptador = new miExpandableAdapter(this, cursos, notas);
		l.setAdapter(adaptador);
		
	}
}

*/