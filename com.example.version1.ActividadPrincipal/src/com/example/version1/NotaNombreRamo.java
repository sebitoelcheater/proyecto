package com.example.version1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.example.controlador.*;
import com.example.data.*;
import com.example.version0.R;
public class NotaNombreRamo extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_nombre_ramo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_nota_nombre_ramo, menu);
        return true;
    }
}
