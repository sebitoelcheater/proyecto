package com.example.version1;

import com.example.version0.R;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

/**
 * @author Adil Soomro
 *
 */
@SuppressWarnings("deprecation")
public class AwesomeActivity extends TabActivity {
	TabHost tabHost;
	/** Called when the activity is first created. */
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tabHost = getTabHost();
		setTabs();
	}
	private void setTabs()
	{
		addTab("Horario", R.drawable.tab_home, ActividadHorario.class);
		addTab("Cursos", R.drawable.tab_search, ActividadRamos.class);
		addTab("Feedback", R.drawable.tab_search, ActividadFeedback.class);
		addTab("Notas", R.drawable.tab_home, ActividadNotas.class);
	}
	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);	
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
}