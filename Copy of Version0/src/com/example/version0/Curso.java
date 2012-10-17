package com.example.version0;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

public class Curso implements Parcelable {
	
	private String id;
	private String nombre;
	private ArrayList<String[]> notas; //CUANDO QUERAMOS PONDERAR COSAS ESTO SERA UNA ESTRUCTURA MAS COMPLEJA QUE UNA LISTA DE INTEGRS
	//ALMACENAR LAS NOTAS COMO UN MAP PARA TENER TAMBIEN SUS IDS RESPECCTIVAS....
	private ArrayList<Modulo> horario;
	
	
	public Curso(Context context,String id)
	{
		this.id = id;
		this.nombre = "";
		this.notas = new ArrayList<String[]>();
		horario = new ArrayList<Modulo>();
		addNota(context,"7");///NO OLVIDAR COMENTAR ESTO
		nuevaActividad(context); //EN VEZ DE NUEVA ACTIVIDAD PODRIA SER NUEVOCURSO....//NUEVOMODULO
	}
	
	

	public Curso(Parcel in)
	{
		readFromParcel(in);
	}
	
	
	
	public void addNota(Context context,String nota) //HACERLO DESDE LA DB AÑANDIR UNA NUEVA NOTA CON EL ID EL CURSO(SEBAAAAAA)
	{
		AdaptadorDBOrganizador db = new AdaptadorDBOrganizador(context);
		db.abrir();        
	    db.insertarNota(id, nota);
		db.cerrar();
		nuevaActividad(context);
	}
	
	public void addModulo(Modulo m) //HACERLO DESDE LA DB AÑADIR UN MODULO CON EL ID DEL CURSO Y LOS OTROS CAMPOS NECESARIOS(SEBAAAA)
	{
		horario.add(m);
	}
	
	
	
	public void changeNota(Context context,int position, int nota) //HACERLO DESDE LA DB CAMBIAR LA NOTA CON EL ID DEL CURSO, LA POSICION DE LA NOTA Y LA NOTA(SEBAAAAA)
	{
		/*
		AdaptadorDBOrganizador db = new AdaptadorDBOrganizador(context);
		String id = ;
		db.actualizarNota(id+"", this.id, ""+nota); 
	    db.cerrar();
	    nuevaActividad(context);
	    */
	}
	
	public void changeModulo(int position, Modulo m) //HACERLO DESDE LA DB CAMBIAR EL MODULO CON EL ID DEL CURSO, Y SUS DATOS ASOCIADOS Y LA POSICION DEL MODULO(SEBAAAAA)
	{
		horario.set(position, m);
	}
	
	public void setNotas(ArrayList<String[]> notas) //HACERLO DESDE LA DB
	{
		this.notas = notas;
	}
	public void setHorario(ArrayList<Modulo> horario) //HACERLO DESDE LA DB
	{
		this.horario = horario;
	}
	public void setNombre(String nombre)
	{
		this.nombre= nombre;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	public String getId()
	{
		return id;
	}
	public String getNota(int position)
	{
		return notas.get(position)[1];
	}
	
	public int getPromedio()
	{
		int sum = 0;
		for(String[] nota : notas)
		{
			sum+=Integer.parseInt(nota[1]);
		}
		return (sum/notas.size());
	}
	public ArrayList<String[]> getNotas()
	{
		return new ArrayList<String[]>(notas);
	}
	
	public ArrayList<String> getListaDeModuloSegunDia(Context context, int dia) //NO OLVIDAR MODIFICAR CON LA DB//NOOOO DESDE LA LISTA DE OBJETOS MODULOS :)
	{
		// TODO Auto-generated method stub
		///MMM ESTO DEBERIA DEVOLVER UNA LISTA DE MODULOS Y DEBERIA SER CONTROLADOR QUIEN GENERE LOS STRING
		ArrayList<String> listaDeModulos = new ArrayList<String>();
		for(Modulo m : horario)
		{
			if(m.getDia()==dia)
				listaDeModulos.add(nombre+": Modulo"+dia);
		}
		
		return listaDeModulos;
	}
	
	
	public void nuevaActividad(Context context) 
	{
		notas = new ArrayList<String[]>();
		cargarNotas(context);
		cargarModulos(context);
		
	}
	
	private void cargarModulos(Context context) { //ACA VA LA PARTE DE CONEXION CON LA BASE DE DATOS NECESITO TODOS LOS MODULOS SEGUN EL ID DEL CURSO(SEBAAAA)
		// TODO Auto-generated method stub
		AdapterHorarios db = new AdapterHorarios(context);
		for(int i=0; i<3;++i)
		{
			Modulo m = new Modulo(context);
			m.setDia((Integer.parseInt(id)%4));//CUIDADO!
			horario.add(m);
		}
	}



	private void cargarNotas(Context context) //ACA VA LA PARTE DE CONEXION CON LA BASE DE DATOS NECESITO LAS NOTAS SEGUN EL ID DEL RAMO(SEBAAAAA)
	{
		AdaptadorDBOrganizador db = new AdaptadorDBOrganizador(context);
		db.abrir();        
	    Cursor c = db.obtenerTodasLasNotasDelCurso(id);
	    if (c.moveToFirst())
        {
            do {
                notas.add(new String[]{c.getString(0) c.getString(1)});
                
            } while (c.moveToNext());
        }
		
		db.cerrar();
		
	}



	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(id);
		dest.writeString(nombre);
		dest.writeList(notas);
		dest.writeList(horario);
	}
	private void readFromParcel(Parcel in) {
		// TODO Auto-generated method stub
		id = in.readString();
		nombre = in.readString();
		notas = new ArrayList<Integer>();
		in.readList(notas,Integer.class.getClassLoader());
		horario = new ArrayList<Modulo>();
		in.readList(horario,Modulo.class.getClassLoader());
	}
	
	public static final Parcelable.Creator CREATOR =
        	new Parcelable.Creator() {
                public Curso createFromParcel(Parcel in) {
                    return new Curso(in);
                }
                public Curso[] newArray(int size) {
                    return new Curso[size];
                }
            };



	

}
