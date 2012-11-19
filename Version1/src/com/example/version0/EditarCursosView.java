package com.example.version0;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.controlador.*;
import com.example.data.*;
import com.example.version0.R;
public class EditarCursosView extends LinearLayout{

	private TextView nombre;
	private Button boton;


	public EditarCursosView(Context context) {
		super(context);
		inflate(context, R.layout.lista_ramos, this);

		/**
		 * Es muy importante guardar las direcciones de los elementos
		 * que vayamos a cambiar pues el findViewById requiere mucho tiempo
		 * y si cada vez que hacemos scroll tenemos que buscar todos los elementos
		 * cargaremos inecesariamente el terminal y perderemos fluidez
		 */

		nombre 		= (TextView) findViewById(R.id.textoNombreRamo);
		boton 		= (Button)	 findViewById(R.id.botonEditarRamo);

		
	}

	/**
	 * Este método nos permitirá asignar los valores a los diferentes
	 * componéntes gráficos según el objeto que queramos ver.
	 * @param rectangulo
	 */
	public void setCurso(UnRamo curso) {
		nombre.setText(""+curso.nombre);
	
	}

}