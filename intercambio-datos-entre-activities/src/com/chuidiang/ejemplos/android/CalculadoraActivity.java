package com.chuidiang.ejemplos.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalculadoraActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.calculadora);

   }

   public void pulsadoBoton(View v) {
      Intent data = new Intent();

      if (v instanceof Button)
         data.putExtra("DATO", ((Button) v).getText());
      else
         data.putExtra("DATO", "A saber qué has pulsado");

      setResult(RESULT_OK, data);
      finish();
   }
}