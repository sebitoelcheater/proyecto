
public class Curso 
{
	private String id;
	private String nombre;
	public Curso(String id)
	{
		this.id = id;
		
		///POR DEFECTO EL CURSO SE CARGA DESDE LA DB
		cargarCursoDesdeDB();  //ESTO IMPLICA QUE CADA CURSO HAR� UNA QUERY...(EFICIENCIA?)
							//ADEMAS NO OLVIDAR QUE TODO ESTO NECESITA UN CONTEXTO,
		
		///POR DEFECTO EL CURSO SE CARGA DESDE LA DB
	}
	
	public void cargarCursoDesdeDB()
	{
		///CARGADOR POR DEFECTO
		nombre = "CURSO"+id;
		///CARGADOR POR DEFECTO
		
		///METODO DE OBTENCION DE DATOS DESDE LA DB
		
		///METODO DE OBTENCION DE DATOS DESDE LA DB
	}
	
	//METODOS DE OBTENCION
		public String obtenerId()
		{
			return id;
		}
		
		public String obtenerNombre()
		{
			return nombre;
		}
	//METODOS DE OBTENCION
		
	//METODOS DE SETEO DEL OBJETO(NO DB)
		public void setId(String id)
		{
			this.id = id;
		}
		
		public void setNombre(String nombre)
		{
			this.nombre = nombre;
		}
	//METODOS DE SETEO DEL OBJETO(NO DB)
		
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO (NO OLVIDAR EL CONTEXTO)
		
		public boolean establecerNombre(String nuevoNombre)
		{
			if(true)
			{	
				setNombre(nuevoNombre);
				return true;
			}
			
			return false;
		}
	// METODOS DE SETEO DE LA BASE DE DATOS Y OBJETO
}
