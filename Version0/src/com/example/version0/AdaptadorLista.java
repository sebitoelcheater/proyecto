/*package de.vogella.android.listactivity;

import java.util.List;

import com.example.version0.ActividadRamos2.UnRamo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.controlador.*;
import com.example.data.*;
public class AdaptadorLista extends BaseAdapter {
  private final Context context;
  private final String[] values;

  class AdaptadorLista extends BaseAdapter{
	    View renderer;
	    List<UnRamo> items; //Hace una lista con los objetos de clase UnRamo

	            // call this one and pass it layoutInflater.inflate(R.layout.my_list_item)
	    

	            // whenever you need to set the list of items just use this method.
	            // call it when you have the data ready and want to display it
	    public void setear(List<UnRamo> items){
	        this.items = items;
	        notifyDataSetChanged();
	    }
	    
	    
	    @Override
	    public int getCount() {
	        return items!=null?items.size():0;
	    }
	    @Override
	    public Object getItem(int position) {
	        return items!=null?items.get(position):null;
	    }
	    /*@Override
	    public long getItemId(int position) {
	        return items!=null?items.get(position).id:-1;*/
	 /*   
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        if(convertView==null){
	            convertView = renderer;
	        }
	        UnRamo item = items.get(position);
	             // replace those R.ids by the ones inside your custom list_item layout.
	        TextView label = (TextView)convertView.findViewById(R.id.textoNombreRamo);
	        label.setText(item.nombre);
	        Button button = (Button)convertView.findViewById(R.id.botonEditarRamo);
	        button.setOnClickListener((android.view.View.OnClickListener) item.listener);
	        return convertView;
	    }


		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}
	}
  
  
  public MySimpleArrayAdapter(Context context, String[] values) {
    super(context, R.layout.rowlayout, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.label);
    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
    textView.setText(values[position]);
    // Change the icon for Windows and iPhone
    String s = values[position];
    if (s.startsWith("iPhone")) {
      imageView.setImageResource(R.drawable.no);
    } else {
      imageView.setImageResource(R.drawable.ok);
    }

    return rowView;
  }
} */