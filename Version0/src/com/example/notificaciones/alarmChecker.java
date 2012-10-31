package com.example.notificaciones;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.example.controlador.Controlador;
import com.example.controlador.Modulo;
import com.example.version0.*;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class alarmChecker extends Service  implements Runnable {

	

public static final int APP_ID_NOTIFICATION = 0; 
private NotificationManager mManager;

private ArrayList<Modulo> proximosInicios;
private ArrayList<Modulo> anterioresFinales;

private final int MSG_KEY_INICIO = 1;
private final int MSG_KEY_FIN = 2;
private int MINUTOS = 10; //POR DEFAULT 10, luego ver como se puede generalzar esto


private int indiceInicio = 0;
private int indiceFin = 0;
public void run() {
	
	Log.e("alarmChecker","ENTRE");
	Calendar ahora = Calendar.getInstance();
	proximosInicios = Controlador.obtenerLosModulosProximosInicio(this, ahora, MINUTOS); 
	anterioresFinales = Controlador.obtenerLosModulosAnterioresFin(this,ahora , MINUTOS);
	for(Modulo m: proximosInicios)
		handler.sendEmptyMessage(MSG_KEY_INICIO);
	for(Modulo m: anterioresFinales)
		handler.sendEmptyMessage(MSG_KEY_FIN);
	
	indiceInicio=0;
	indiceFin=0;
		
}


private Handler handler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
			switch (msg.what){
			case MSG_KEY_INICIO: //Hemos obtenido un numero par
				 NotificarInicio();
				break;
			case MSG_KEY_FIN:
				NotificarFin();
				break;
			}
	}
};


private void NotificarInicio() {
		
	//Esta ser� la actividad de verdad que llamara la aplicacion
		Intent intentNot = new Intent(this, ActividadHorario.class);
		
	//Prepara la notificacion
		Notification notification = new Notification(R.drawable.ic_launcher, "Inicio cerca", System.currentTimeMillis());
		notification.setLatestEventInfo(this, proximosInicios.get(indiceInicio).obtenerNombre(), "A las "+proximosInicios.get(indiceInicio).obtenerStringInicio(), 
				PendingIntent.getActivity(this.getBaseContext(), 0, intentNot, PendingIntent.FLAG_CANCEL_CURRENT));
		
		mManager.notify(APP_ID_NOTIFICATION, notification);
		
		++indiceInicio;
	 }

private void NotificarFin() {
	
	//Esta ser� la actividad de verdad que llamara la aplicacion
		Intent intentNot = new Intent(this, ActividadHorario.class);
		
	//Prepara la notificacion
		Notification notification = new Notification(R.drawable.ic_launcher, "Fin pasado", System.currentTimeMillis());
		notification.setLatestEventInfo(this, "Clase terminada", anterioresFinales.get(indiceFin).obtenerNombre(), 
				PendingIntent.getActivity(this.getBaseContext(), 0, intentNot, PendingIntent.FLAG_CANCEL_CURRENT));
		
		mManager.notify(APP_ID_NOTIFICATION, notification);
		
		++indiceFin;
		
	 }


	  
	  
@Override
public void onCreate() {
 // TODO Auto-generated method stub
// Toast.makeText(this, "MyAlarmService.onCreate()", Toast.LENGTH_LONG).show();
 
 mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
}

@Override
public IBinder onBind(Intent intent) {
 // TODO Auto-generated method stub
// Toast.makeText(this, "MyAlarmService.onBind()", Toast.LENGTH_LONG).show();
 return null;
}

@Override
public void onDestroy() {
 // TODO Auto-generated method stub
 super.onDestroy();
// Toast.makeText(this, "MyAlarmService.onDestroy()", Toast.LENGTH_LONG).show();
}

@Override
public void onStart(Intent intent, int startId) {
 // TODO Auto-generated method stub
 super.onStart(intent, startId);
// Toast.makeText(this, "MyAlarmService.onStart()", Toast.LENGTH_LONG).show();
 
//Creamos un hilo que obtendra la informaci�n de forma as�ncrona
	Thread thread = new Thread(this);
	thread.start();
}

	 


@Override
public boolean onUnbind(Intent intent) {
 // TODO Auto-generated method stub
// Toast.makeText(this, "MyAlarmService.onUnbind()", Toast.LENGTH_LONG).show();
 return super.onUnbind(intent);
}

}