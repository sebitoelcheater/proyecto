package com.example.version0;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Principal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal, menu);
        return true;
    }
}
