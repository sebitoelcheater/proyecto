import java.util.*;


public class Modulo

{
	private String nombre;
	private Calendar inicio;
	private Calendar fin;
	
	public Modulo()
	{
		this.nombre = "";
		this.horaInicio="";
		this.horaFin="";
		this.dia = -1;
		nuevaActividad(context);
	}
	
	

	



	public Modulo(Parcel in)
	{
		readFromParcel(in);
	}
	
	public void setDia(int dia)
	{
		this.dia= dia;
	}
	public void setNombre(String nombre)
	{
		this.nombre=nombre;
	}
	public void setHoraInicio(String horaInicio)
	{
		this.horaInicio = horaInicio;
	}
	public void setHoraFin(String horaFin)
	{
		this.horaFin = horaFin;
	}
	
	public int getDia()
	{
		return dia;
	}
	public String getNombre()
	{
		return nombre;
	}
	public String getHoraInicio()
	{
		return horaInicio;
	}
	public String getHoraFin()
	{
		return horaFin;
	}
