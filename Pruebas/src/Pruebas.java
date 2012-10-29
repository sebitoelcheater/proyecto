
import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
public class Pruebas {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{

		SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
		Date a = new Date();
		Calendar inicio = new GregorianCalendar();
	    
		try
		{
			a = formato.parse("13:13");
			inicio.setTime(a);
		}catch(ParseException e){
			System.out.println("HOLA");
		}
		
		System.out.println("DIA: " + inicio.get(Calendar.MINUTE));
		
		
	}

	private static String agregarCeros(int n, int i) {
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
