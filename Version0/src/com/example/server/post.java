package com.example.server;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class post extends Activity {

	public void comentar (View view, String ramo, String comentario) {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        ramo=ramo.replaceAll(" ", "%20");
        comentario=comentario.replaceAll(" ", "%20");
        HttpPost httppost = new HttpPost("http://www.cheaper.cl/android/signup.php?ramo="+ramo+"&comentario="+comentario+"");

        try {
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);
            
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
	}
}