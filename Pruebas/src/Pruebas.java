
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
		// TODO Auto-generated method stub
		System.out.println("Hola bienvenido al sistema de pruebas de Calendar");
		BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));
		String [] dias = new String[]{"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
		Calendar calendario = Calendar.getInstance();
		System.out.println("Hoy es : "+dias[calendario.get(Calendar.DAY_OF_WEEK)-1]);
		
		Calendar ma�ana = (Calendar)calendario.clone();
		ma�ana.add(Calendar.DATE, 1);
		//Calendar ma�ana = new GregorianCalendar(calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)+1);
		System.out.println("Ma�ana sera : " + dias[ma�ana.get(Calendar.DAY_OF_WEEK)]);
		
		if(ma�ana.before(calendario))
		{
			System.out.println("Ma�ana es antes que Calendario");
		}
		if(calendario.before(ma�ana))
		{
			System.out.println("Calendario es antes que Ma�ana");
		}
		if(ma�ana.after(calendario))
		{
			System.out.println("Ma�ana es depues que Calendario");
		}
		if(calendario.after(ma�ana))
		{
			System.out.println("Calendario es despues que Ma�ana");
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = new Date();
		Calendar myCalendario = new GregorianCalendar();
		
		try
		{
			myDate = formato.parse("2012-10-24 13:06:25");
			
			myCalendario.setTime(myDate);
		}catch(ParseException e){}
		
		System.out.println("Dia="+myCalendario.get(Calendar.DATE));
		System.out.println("Mes="+myCalendario.get(Calendar.MONTH));
		System.out.println("A�o="+myCalendario.get(Calendar.YEAR));
		
		//System.out.println(agregarCeros(4,calendario.get(Calendar.YEAR))+"-"+agregarCeros(2,calendario.get(Calendar.MONTH))+"-"+agregarCeros(2,calendario.get(Calendar.DATE))+" "+agregarCeros(2,calendario.get(Calendar.HOUR_OF_DAY))+":"+agregarCeros(2,calendario.get(Calendar.MINUTE))+":"+agregarCeros(2,calendario.get(Calendar.SECOND)));
		System.out.println(new Boolean(true));
		
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
