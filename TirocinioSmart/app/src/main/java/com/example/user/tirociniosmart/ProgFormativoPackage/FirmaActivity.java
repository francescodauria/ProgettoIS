package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.github.gcacace.signaturepad.views.SignaturePad;

/**
 * Created by User on 29/10/2017.
 */

public class FirmaActivity extends Activity {
    View view;
    SignaturePad signaturePad;
    Button saveButton, clearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frament_inserimento_firma_layout);

           saveButton = (Button)findViewById(R.id.saveButton);
          clearButton = (Button)findViewById(R.id.clearButton);

          saveButton.setEnabled(false);
          clearButton.setEnabled(false);
          signaturePad = (SignaturePad)findViewById(R.id.signaturePad);

        //change screen orientation to landscape mode
        signaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {

            }

            @Override
            public void onSigned() {
                saveButton.setEnabled(true);
                clearButton.setEnabled(true);
            }

            @Override
            public void onClear() {
                saveButton.setEnabled(false);
                clearButton.setEnabled(false);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write code for saving the signature here
                Intent returnIntent = new Intent(FirmaActivity.this, StudentActivity.class);
                returnIntent.putExtra("firma","Simone");
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
