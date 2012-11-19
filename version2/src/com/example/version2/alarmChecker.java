

package com.example.version2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import com.example.controlador.Controlador;
import com.example.controlador.Modulo;
import com.example.version2.ActividadHorario;
import com.example.version2.R;

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

	private int indiceInicio;
	private int indiceFin;

/**
 * M�todo del hilo as�ncrono, que obtiene un numero aleatorio y comprueba su paridad
 */
public void run() {
	
	indiceInicio=0;
	indiceFin=0;
	
	Calendar ahora = Calendar.getInstance();
	proximosInicios = Controlador.obtenerLosModulosProximosInicio(this, ahora, MINUTOS); 
	anterioresFinales = Controlador.obtenerLosModulosAnterioresFin(this,ahora , MINUTOS);
	
	/*
	for(Modulo m : proximosInicios)
		Log.d("MODULO",m.obtenerNombre());
	*/
	handler.sendEmptyMessage(MSG_KEY_INICIO);
	
	
	
	
}


/**
 * Procesa eventos desde el hilo run
 */
private Handler handler = new Handler() {
	@Override
	public void handleMessage(Message msg) {
			switch (msg.what){
			case MSG_KEY_INICIO: //Hemos obtenido un numero par
				 Notificar();
				break;
			}
	}
};


/**
 * prepara y lanza la notificacion
 */
private void Notificar() {
		
	//Esta ser� la actividad de verdad que llamara la aplicacion
	if(proximosInicios.size()!=0)
	{	
		Intent intentNot = new Intent(this, ActividadHorario.class);
	
		//Prepara la notificacion
		Notification notification = new Notification(android.R.drawable.ic_menu_my_calendar, "Pr�xima Clase", System.currentTimeMillis());
		notification.setLatestEventInfo(this, proximosInicios.get(0).obtenerNombre(), "A las "+proximosInicios.get(0).obtenerStringInicio(), 
				PendingIntent.getActivity(this.getBaseContext(), 0, intentNot, PendingIntent.FLAG_CANCEL_CURRENT));
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		mManager.notify(Integer.parseInt(proximosInicios.get(0).obtenerId()), notification); ///PENSAR MEJOR ESTO Y �COMO HACER QUE NO APARESCA DOS VECES?
	}
	if(anterioresFinales.size() != 0)
	{
		Intent intentNot = new Intent(this, ActividadFeedback.class);
		
		//Prepara la notificacion
		Notification notification = new Notification(android.R.drawable.ic_menu_send, "Comenta!", System.currentTimeMillis());
		notification.setLatestEventInfo(this, "Comentar "+anterioresFinales.get(0).obtenerNombre(), "Da tu feedback!", 
				PendingIntent.getActivity(this.getBaseContext(), 0, intentNot, PendingIntent.FLAG_CANCEL_CURRENT));
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		mManager.notify(Integer.parseInt(anterioresFinales.get(0).obtenerId())+1, notification); ///PENSAR MEJOR ESTO  Y �COMO HACER QUE NO APARESCA DOS VECES?
	}	
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