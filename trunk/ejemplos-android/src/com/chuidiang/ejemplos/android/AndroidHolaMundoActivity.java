package com.chuidiang.ejemplos.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AndroidHolaMundoActivity extends Activity {
   /** Called when the activity is first created. */
   @Override
   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      Button boton = (Button) findViewById(R.id.buton);
      boton.setOnClickListener(new OnClickListener() {

         @Override
         public void onClick(View v) {
            Intent intent = new Intent(AndroidHolaMundoActivity.this,
                  CalculadoraActivity.class);
            startActivityForResult(intent, 11);

         }
      });
   }

   @Override
   public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      TextView tv = (TextView) findViewById(R.id.textView);

      if ((resultCode == RESULT_OK) && (requestCode == 11)) {
         tv.setText(data.getExtras().getCharSequence("DATO"));
      } else {
         if (resultCode != RESULT_OK)
            tv.setText("No es ResultOK");
         else
            tv.setText("No es 11");
      }
   }
}