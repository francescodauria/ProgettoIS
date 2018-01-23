package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.GenericConnectionPool;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.IdentityHashMap;

public class RegistrazioneTutorActivity extends AppCompatActivity {
    public static final int GET_FROM_GALLERY = 3;
    private ImageView logo;
    private static MySQLConnectionPoolFreeSqlDB pool;
    private EditText nome_tutor;
    private EditText cognome_tutor;
    private EditText CF;
    private EditText mail_tutor;
    private EditText username;
    private EditText password;
    private EditText numero_tutor;
    private EditText IDazienda;
    private EditText nome_azienda;
    private EditText sede_azienda;
    private EditText descrizione;
    private EditText numero_azienda;
    private EditText email_azienda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione_tutor);
        logo = findViewById(R.id.logoAzienda);
        pool = new MySQLConnectionPoolFreeSqlDB();
        nome_tutor=(EditText)findViewById(R.id.nomeTutor);
        cognome_tutor=(EditText)findViewById(R.id.cognomeTutor);
        CF=(EditText)findViewById(R.id.cfTutor);
        mail_tutor=(EditText)findViewById(R.id.emailTutor);
        username=(EditText)findViewById(R.id.usernameTutor);
        password=(EditText)findViewById(R.id.passwordTutor);
        numero_tutor=(EditText)findViewById(R.id.telefonoTutor);
        IDazienda=(EditText)findViewById(R.id.idAzienda);
        nome_azienda=(EditText)findViewById(R.id.nomeAzienda);
        sede_azienda=(EditText)findViewById(R.id.sedeAzienda);
        descrizione=(EditText)findViewById(R.id.descrizioneAzienda);
        numero_azienda=(EditText)findViewById(R.id.numeroAzienda);
        email_azienda=(EditText)findViewById(R.id.emailAzienda);
        logo=(ImageView)findViewById(R.id.logoAzienda);

    }

    public void effettuaRegistrazione(View view) {
        View focusView = null;

        if(TextUtils.isEmpty(nome_tutor.getText().toString())) {
            nome_tutor.setError("Il campo nome tutor non \npuò essere vuoto");
            focusView = nome_tutor;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(cognome_tutor.getText().toString())) {
            cognome_tutor.setError("Il campo cognome tutor non \npuò essere vuoto");
            focusView = cognome_tutor;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(CF.getText().toString())) {
            CF.setError("Il campo CF tutor non \npuò essere vuoto");
            focusView = CF;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(mail_tutor.getText().toString())) {
            mail_tutor.setError("Il campo mail tutor non \npuò essere vuoto");
            focusView = mail_tutor;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(username.getText().toString())) {
            username.setError("Il campo username tutor non \npuò essere vuoto");
            focusView = username;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Il campo password tutor non \npuò essere vuoto");
            focusView = password;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(numero_tutor.getText().toString())) {
            numero_tutor.setError("Il campo numero tutor non \npuò essere vuoto");
            focusView = numero_tutor;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(IDazienda.getText().toString())) {
            IDazienda.setError("Il campo ID azienda non \npuò essere vuoto");
            focusView = IDazienda;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(nome_azienda.getText().toString())) {
            nome_azienda.setError("Il campo nome azienda non \npuò essere vuoto");
            focusView = nome_azienda;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(sede_azienda.getText().toString())) {
            sede_azienda.setError("Il campo sede azienda non \npuò essere vuoto");
            focusView = sede_azienda;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(descrizione.getText().toString())) {
            descrizione.setError("Il campo descrizione azienda non \npuò essere vuoto");
            focusView = descrizione;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(numero_azienda.getText().toString())) {
            numero_azienda.setError("Il campo numero azienda non \npuò essere vuoto");
            focusView = numero_azienda;
            focusView.requestFocus();
        }else if(TextUtils.isEmpty(email_azienda.getText().toString())) {
            email_azienda.setError("Il campo email azienda non \npuò essere vuoto");
            focusView = email_azienda;
            focusView.requestFocus();
        }else if(null==logo.getDrawable()) {
            Toast.makeText(this, "Il campo logo azienda non può essere vuoto", Toast.LENGTH_SHORT).show();
        }else{
            new RegistrazioneAziendaTask().execute(1);
        }


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


    class RegistrazioneAziendaTask extends AsyncTask<Integer, Integer, String > {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            String s;
            Drawable logo1=logo.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)logo1).getBitmap();

            Azienda a=new Azienda(IDazienda.getText().toString(),nome_azienda.getText().toString(),email_azienda.getText().toString(),sede_azienda.getText().toString(),descrizione.getText().toString(),bitmap,numero_azienda.getText().toString(),null);
            TutorAz tutor=new TutorAz(username.getText().toString(),password.getText().toString(),"TUTOR_AZIENDALE",nome_tutor.getText().toString(),cognome_tutor.getText().toString(),CF.getText().toString(),mail_tutor.getText().toString(),numero_tutor.getText().toString(),a);
            AziendaDAO.setConnectionPool(pool);
            TutorAziendaleDAO.setConnectionPool(pool);
            try{
                AziendaDAO.insert(a);
                TutorAziendaleDAO.insert(tutor,a.getId());
                s="Inserimento azienda e tutor avvenuto correttamente";

            } catch (SQLException e) {
                s="Inserimento azienda e tutor non avvenuto correttamente";
                e.printStackTrace();

            }
            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {


            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }


    }



}