package com.example.holita;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
EditText user;
EditText pass;
Button validar;

@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);

user = (EditText) findViewById(R.id.txtUsuario);
pass = (EditText) findViewById(R.id.txtPass);
validar = (Button) findViewById(R.id.btnValidar);

validar.setOnClickListener(new OnClickListener() {
public void onClick(View v) {
ArrayList parametros = new ArrayList();
parametros.add("Usuario");
parametros.add(user.getText().toString());
parametros.add("Contrasena");
parametros.add(pass.getText().toString());
// Llamada a Servidor Web PHP
try {
                        Post post = new Post();
                        JSONArray datos = post.getServerData(parametros,
                                                                       "http://cheaper.cl/android/login.php");
// No se puede poner localhost, carga la consola de Windows
// y escribe ipconfig/all para ver tu IP
if (datos != null && datos.length() > 0) {
	
	
                        JSONObject json_data = datos.getJSONObject(0);
                        int numRegistrados = json_data.getInt("ID_USUARIO");
if (numRegistrados > 0) {
                        Toast.makeText(getBaseContext(),
                                                                       "Usuario correcto. ", Toast.LENGTH_SHORT)
                                                                                              .show();
                        }
} else {
                        Toast.makeText(getBaseContext(),
                                                                       "Usuario incorrecto. ", Toast.LENGTH_SHORT)
                                                                                              .show();
                        }
} catch (Exception e) {Post post = new Post();JSONArray dotos = post.getServerData(parametros,
        "http://cheaper.cl/android/login.php");
                        Toast.makeText(getBaseContext(),
                        		" Bienvenido :) "+dotos,
                                                                       Toast.LENGTH_SHORT).show();
}
// FIN Llamada a Servidor Web PHP

                                               }
                        });
}

class Post {
                        private InputStream is = null;
                        private String respuesta = "";

private void conectaPost(ArrayList parametros, String URL) {
                        ArrayList nameValuePairs;
                        try {

                                               HttpClient httpclient = new DefaultHttpClient();

                                               HttpPost httppost = new HttpPost(URL);
                                               nameValuePairs = new ArrayList();

                                               if (parametros != null) {
                                                                       for (int i = 0; i < parametros.size() - 1; i += 2) {
nameValuePairs.add(new BasicNameValuePair((String)parametros.get(i), (String)parametros.get(i + 1)));
                                                                       }
httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                                               }
                                               HttpResponse response = httpclient.execute(httppost);
                                               HttpEntity entity = response.getEntity();
                                               is = entity.getContent();
                        } catch (Exception e) {

                                               Log.e("log_tag", "Error in http connection " + e.toString());

                        } finally {

                        }
}

private void getRespuestaPost() {
try {
BufferedReader reader = new BufferedReader(
new InputStreamReader(is, "iso-8859-1"), 8);
StringBuilder sb = new StringBuilder();
String line = null;
while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
}
is.close();
respuesta = sb.toString();
Log.e("log_tag", "Cadena JSon " + respuesta);
} catch (Exception e) {

                        Log.e("log_tag", "Error converting result " + e.toString());

                        }
}

@SuppressWarnings("finally")
private JSONArray getJsonArray() {
                        JSONArray jArray = null;
                        try {

                                               jArray = new JSONArray(respuesta);

                        } catch (Exception e) {

                        } finally {
                                               return jArray;
                        }
}

public JSONArray getServerData(ArrayList parametros, String URL) {
                        conectaPost(parametros, URL);
                        if (is != null) {
                                               getRespuestaPost();
                        }
                        if (respuesta != null && respuesta.trim() != "") {
                                                                                              return getJsonArray();
                                                                       } else {
                                                                                              return null;
                                                                       }
                                               }
                        }
}