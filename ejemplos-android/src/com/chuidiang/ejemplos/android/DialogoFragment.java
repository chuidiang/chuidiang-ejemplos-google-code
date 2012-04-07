package com.chuidiang.ejemplos.android;

import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class DialogoFragment extends DialogFragment {

   @Override
   public void onAttach(Activity activity) {
      super.onAttach(activity);
      if (activity instanceof FormularioListener) {
         listener = (FormularioListener) activity;
      }

   }

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
         Bundle savedInstanceState) {
      View vista = inflater.inflate(R.layout.formulario, container, false);
      ((Button) vista.findViewById(R.id.aceptar))
            .setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {
                  pulsadoBoton(v);
               }
            });
      ((Button) vista.findViewById(R.id.cancelar))
            .setOnClickListener(new OnClickListener() {

               @Override
               public void onClick(View v) {
                  pulsadoBoton(v);
               }
            });
      getDialog().setTitle("Título del diálogo");

      return vista;
   }

   public interface FormularioListener {
      public void pulsado(int resultado, String texto);
   }

   public void pulsadoBoton(View v) {
      if (null == listener) {
         return;
      }
      if (((Button) v).getText().equals("Ok")) {
         listener.pulsado(OK,
               ((EditText) getDialog().findViewById(R.id.textoForumulario))
                     .getText().toString());
      } else {
         listener.pulsado(CANCEL, "");
      }
   }

   public final static int OK = 0;
   public final static int CANCEL = 1;
   private FormularioListener listener;
}