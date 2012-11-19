package com.example.version2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class ActividadHorarioSemanal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario_semanal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario_semanal, menu);
        return true;
    }
}
