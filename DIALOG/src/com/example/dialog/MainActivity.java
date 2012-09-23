package com.example.dialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    
    private static final int DIALOGO_TIPO_1 = 1;
    private static final int DIALOGO_TIPO_2 = 2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.button1);
        b.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Button b = (Button)findViewById(R.id.button1);
		b.setText("hola");
		showDialog(1);
	}
    
	protected Dialog onCreateDialog(int id) {
	    Dialog dialogo = null;
	 
	    switch(id)
	    {
	        case DIALOGO_TIPO_1:
	            dialogo = crearDialogo1();
	            break;
	        case DIALOGO_TIPO_2:
	            dialogo = crearDialogo2();
	            break;
	        default:
	            dialogo = null;
	            break;
	    }
	 
	    return dialogo;
	}

	private Dialog crearDialogo2() {
		// TODO Auto-generated method stub
		Dialog d = new Dialog(this);
		d.setContentView(R.layout.activity_dialogo);
		d.setTitle("Editor Nota");
		
		
		
		return d;
	}

	private Dialog crearDialogo1() {
		// TODO Auto-generated method stub
		Dialog d = new Dialog(this);
		d.setContentView(R.layout.activity_dialogo);
		d.setTitle("Editor Nota");
		
		
		
		return d;
	}
}
