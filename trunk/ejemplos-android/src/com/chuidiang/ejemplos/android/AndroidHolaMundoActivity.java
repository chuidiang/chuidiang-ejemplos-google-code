package com.chuidiang.ejemplos.android;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.chuidiang.ejemplos.android.FormularioFragment.FormularioListener;

public class AndroidHolaMundoActivity extends Activity implements
      FormularioListener {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      Button boton = (Button) findViewById(R.id.buton);
      boton.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            FragmentManager fm = getFragmentManager();
            Fragment editor = fm.findFragmentByTag("editor");
            if (null == editor) {
               FragmentTransaction ft = fm.beginTransaction();
               ft.add(R.id.contendorFormulario, new FormularioFragment(),
                     "editor");
               ft.commit();
            }
         }
      });
   }

   @Override
   public void pulsado(int resultado, String texto) {
      TextView tv = (TextView) findViewById(R.id.textView);
      if (resultado == FormularioFragment.OK) {
         tv.setText(texto);
      }
      FragmentManager fm = getFragmentManager();
      Fragment editor = fm.findFragmentByTag("editor");
      FragmentTransaction ft = fm.beginTransaction();
      ft.remove(editor);
      ft.commit();
   }
}