package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;

/**
 * Created by User on 29/10/2017.
 */

public class FirmaActivity extends Activity {
    View view;
    SignaturePad signaturePad;
    Button saveButton, clearButton;
    int position;

    /**
     *
      * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserimento_firma_layout);

           saveButton = (Button)findViewById(R.id.saveButton);
          clearButton = (Button)findViewById(R.id.clearButton);

          position = getIntent().getIntExtra("position",1);

          saveButton.setEnabled(false);
          clearButton.setEnabled(false);
          signaturePad = (SignaturePad)findViewById(R.id.signaturePad);

        //change screen orientation to landscape mode
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            /**
             *
              */
            @Override
            public void onStartSigning() {

            }

            /**
             *
             */
            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            /**
             *
             */
            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            /**
             *
              * @param v
             */
            @Override
            public void onClick(View v) {

                Bitmap image = signaturePad.getSignatureBitmap();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();
                //write code for saving the signature here
                Intent returnIntent = new Intent(FirmaActivity.this, StudentActivity.class);
                returnIntent.putExtra("bitmapdata",bitmapdata);
                returnIntent.putExtra("position",position);


                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signaturePad.clear();
            }
        });




    }


}
