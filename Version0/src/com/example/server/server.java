package com.example.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class server extends Activity {

	public void comentar (View view, int i, String comentario) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        comentario=comentario.replaceAll(" ", "%20");
        HttpPost httppost = new HttpPost("http://www.cheaper.cl/android/signup.php?ramo="+i+"&comentario="+comentario+"");

        try {
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
	}
	
	public String getInternetData(String URL) throws Exception {
		BufferedReader in = null;
		String data = null;
		try{
			HttpClient client = new DefaultHttpClient();
			URI website = new URI(URL);
			HttpGet request = new HttpGet();
			request.setURI(website);
			HttpResponse response = client.execute(request);
			in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer sb = new StringBuffer("");
			String l = "";
			String nl = System.getProperty("line.separator");
			while ((l = in.readLine()) !=null){
				sb.append(l + nl);
			}
			in.close();
			data = sb.toString();
			return data;
		}finally{
			if (in != null){
				try{
					in.close();
					return data;
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<JSONObject> getCursoFromDatabase(String idC,String elemento) throws Exception{
		String URL = "http://www.cheaper.cl/android/suscribir.php?id="+idC+"";
		String JSONChain = getInternetData(URL);
		stringToJSON a = new stringToJSON();
		ArrayList<JSONObject> arreglo = a.getArray(JSONChain,elemento);
		return arreglo;
	}
	
	public void suscribirCurso (String id) throws Exception{
		ArrayList<JSONObject> Profesor = getCursoFromDatabase(id,"Profesores");
		ArrayList<JSONObject> Curso = getCursoFromDatabase(id,"Cursos");
		ArrayList<JSONObject> Horarios = getCursoFromDatabase(id,"Horarios");
		ArrayList<JSONObject> Comentarios = getCursoFromDatabase(id,"Comentarios");
		
		for (int i = 0; i < Profesor.size(); i++) {
			String idP = Profesor.get(i).getString("idP");
			String usuario = Profesor.get(i).getString("usuario");
			String contrasena = Profesor.get(i).getString("contrasena");
			String nombre = Profesor.get(i).getString("nombre");
			String apellido = Profesor.get(i).getString("apellido");
        	// introducir nuevo profesor (si no est‡ introducido). Lo obtengo a partir de un for, pero es claro que arrojar‡ s—lo un elemento
        }
		
		for (int i = 0; i < Curso.size(); i++) {
			String idC = Curso.get(i).getString("idC");
			String idP = Curso.get(i).getString("idP");
			String titulo = Curso.get(i).getString("titulo");
			String comentable = Curso.get(i).getString("comentable");
        	// introducir nuevo curso con funciones hechas por Ariel, con los par‡metros declarados en este for. Lo mismo para profe,horarios y comentarios
        }
		
		for (int i = 0; i < Horarios.size(); i++) {
			String idH=Horarios.get(i).getString("idH");
			String idC=Horarios.get(i).getString("idC");
			String dds=Horarios.get(i).getString("dds");
			String inicio=Horarios.get(i).getString("inicio");
			String fin=Horarios.get(i).getString("fin");
			String ubicacion=Horarios.get(i).getString("ubicacion");
        	// introducir nuevo horarios 
        }
		
		for (int i = 0; i < Comentarios.size(); i++) {
			String idCom = Comentarios.get(i).getString("idCom");
			String idH = Comentarios.get(i).getString("idH");
			String fecha = Comentarios.get(i).getString("fecha");
			String comentario = Comentarios.get(i).getString("comentario");
        	// introducir nuevo comentarios 
        }
		
		
		// forma de obtener el campo "name" del usuario de idP 1 Profesor.get(1).getString("name");
		
	}
	
	
	
	
	
}