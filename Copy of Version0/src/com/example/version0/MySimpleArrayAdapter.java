package com.example.version0;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MySimpleArrayAdapter extends ArrayAdapter<UnRamo> {
  private List<UnRamo> objects;
   
  public MySimpleArrayAdapter(Context context, int textViewResourceId, List<UnRamo> listaRamos) {
		super(context, textViewResourceId, listaRamos);
		this.objects = listaRamos;
	}
  public void clicEditarRamo(String id) {
}


  @Override
  public View getView(int position, View convertView, ViewGroup parent) {	  
	  
	  
	  UnRamo item = objects.get(position);
	  
      // replace those R.ids by the ones inside your custom list_item layout.
	  TextView label = (TextView)convertView.findViewById(R.id.textoNombreRamo);
	  label.setText(item.nombre);
	  Button button = (Button)convertView.findViewById(R.id.botonEditarRamo);
	  button.setOnClickListener((android.view.View.OnClickListener) item.listener);
	  return convertView;
  }
} 