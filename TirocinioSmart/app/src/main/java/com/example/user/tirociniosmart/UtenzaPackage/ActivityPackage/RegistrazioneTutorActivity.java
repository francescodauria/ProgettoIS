package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.GenericConnectionPool;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class RegistrazioneTutorActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 3;
    private ImageView logo;
    private static MySQLConnectionPoolFreeSqlDB pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_tutor);
        logo = findViewById(R.id.logoAzienda);
        pool = new MySQLConnectionPoolFreeSqlDB();

    }

    public void effettuaRegistrazione(View view) {

    }

    public void caricaLogo(View view) {

        startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                logo.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {

                Toast.makeText(this, "Attenzione, errore nel caricamento", Toast.LENGTH_LONG).show();

                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onStop(){

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onStop();
    }




}