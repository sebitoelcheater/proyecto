package com.example.version2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActividadHorarioSemanal extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_horario_semanal);
        LinearLayout l = (LinearLayout)findViewById(R.id.LinearLayoutLunes);
        l.addView(new Button(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_actividad_horario_semanal, menu);
        return true;
    }
}
