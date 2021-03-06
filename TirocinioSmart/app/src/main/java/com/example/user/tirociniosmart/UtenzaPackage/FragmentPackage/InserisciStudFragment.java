package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.RichiediConvenzioneFragment;
import com.example.user.tirociniosmart.DAOPackage.StudenteDAO;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.ProgFormativoPackage.ObiettiviFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.SegreteriaActivity;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class InserisciStudFragment extends android.app.Fragment implements View.OnClickListener,DatePickerDialog.OnDateSetListener {

    private Button insert;
    private View view;
    private View mProgressView;
    EditText nome;
    EditText cognome;
    EditText matricola;
    EditText username;
    EditText password;
    EditText passwordRipetuta;
    EditText mail;
    EditText telefono;
    EditText indirizzo;
    EditText luogoNascita;
    EditText numeroTirocini;
    EditText codiceFiscale;
    TextView dataNascita;
    Date data_N;

    /**
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_inserisci_stud, container, false);
        mProgressView = view.findViewById(R.id.insert_studente_progress);

        insert = view.findViewById(R.id.buttonInsertStudente);
        showProgress(false);

        nome = view.findViewById(R.id.nomeStudente);
        cognome = view.findViewById(R.id.cognomeStudente);
        matricola= view.findViewById(R.id.matricolaStudente);
        username= view.findViewById(R.id.usernameStudente);
        password= view.findViewById(R.id.passwordStudente);
        passwordRipetuta= view.findViewById(R.id.passwordRipetutaStudente);
        mail= view.findViewById(R.id.emailStudente);
        telefono= view.findViewById(R.id.telefonoStudente);
        indirizzo= view.findViewById(R.id.indirizzoStudente);
        luogoNascita= view.findViewById(R.id.luogoNascitaStudente);
        numeroTirocini= view.findViewById(R.id.tirociniStudente);
        codiceFiscale= view.findViewById(R.id.cfStudente);
        dataNascita = view.findViewById(R.id.dataNascitaStudente);

        dataNascita.setOnClickListener(this);
        insert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                View focusView = null;

                if(TextUtils.isEmpty(nome.getText().toString())) {
                    nome.setError("Il campo nome non \npuò essere vuoto");
                    focusView = nome;
                    focusView.requestFocus();
                }
                else if (TextUtils.isEmpty(cognome.getText().toString())) {
                    cognome.setError("Il campo cognome non \npuò essere vuoto");
                    focusView = cognome;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(matricola.getText().toString())) {
                    matricola.setError("Il campo matricola non \npuò essere vuoto");
                    focusView = matricola;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(username.getText().toString())) {
                    username.setError("Il campo username non \npuò essere vuoto");
                    focusView = username;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(password.getText().toString())) {
                    password.setError("Il campo password non \npuò essere vuoto");
                    focusView = password;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(passwordRipetuta.getText().toString())) {
                    passwordRipetuta.setError("Il campo password ripetuta non \npuò essere vuoto");
                    focusView = passwordRipetuta;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(mail.getText().toString())) {
                    mail.setError("Il campo email non \npuò essere vuoto");
                    focusView = mail;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(telefono.getText().toString())) {
                    telefono.setError("Il campo telefono non \npuò essere vuoto");
                    focusView = telefono;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(indirizzo.getText().toString())) {
                    indirizzo.setError("Il campo indirizzo non \npuò essere vuoto");
                    focusView = indirizzo;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(luogoNascita.getText().toString())) {
                    luogoNascita.setError("Il campo \nluogo di nascita non \npuò essere vuoto");
                    focusView = luogoNascita;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(numeroTirocini.getText().toString())) {
                    numeroTirocini.setError("Il campo numero tirocini non \npuò essere vuoto");
                    focusView = numeroTirocini;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(codiceFiscale.getText().toString())) {
                    codiceFiscale.setError("Il campo CF non \npuò essere vuoto");
                    focusView = codiceFiscale;
                    focusView.requestFocus();
                }
                else if(dataNascita.getText().toString().equalsIgnoreCase("Seleziona data"))
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Attenzione, il campo data deve essere settato",Toast.LENGTH_LONG).show();
                }
                else if(!(password.getText().toString().length()>5)){
                    password.setError("Il campo password deve \navere almeno 6 caratteri");
                    focusView = password;
                    focusView.requestFocus();
                }
                else if(!(codiceFiscale.getText().toString().length()==16)){
                    codiceFiscale.setError("Il campo codice fiscale \nnon è settato correttamente");
                    focusView = codiceFiscale;
                    focusView.requestFocus();
                }
                else if(!password.getText().toString().equals(passwordRipetuta.getText().toString())){
                    passwordRipetuta.setError("Attenzione, le password devono coincidere");
                    focusView = passwordRipetuta;
                    focusView.requestFocus();
                }
                else
                    new InsertStudente().execute(1);
            }


        });


        return view;
    }

    /**
     *
     * @param show
     */
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }


    class InsertStudente extends AsyncTask<Integer, Integer, String > {
        /**
         *
         */
        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        /**
         *
         * @param img_ids
         * @return
         */
        @Override
        protected String doInBackground(Integer... img_ids) {

            Studente studente = new Studente(username.getText().toString(),password.getText().toString(),"Studente",matricola.getText().toString(),nome.getText().toString(),cognome.getText().toString(),codiceFiscale.getText().toString(),mail.getText().toString(),indirizzo.getText().toString(),luogoNascita.getText().toString(),data_N,Integer.parseInt(numeroTirocini.getText().toString()),numeroTirocini.getText().toString(),null);

            StudenteDAO.setConnectionPool(SegreteriaActivity.pool);
            String s =StudenteDAO.insert(studente);
            return s;
        }

        /**
         *
         * @param values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        /**
         *
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }


    }

    /**
     *
     * @param datePicker
     * @param i
     * @param i1
     * @param i2
     */
    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            String data=""+i2+"/"+(i1+1)+"/"+i;
            data_N=new Date(i,i1,i2);
            dataNascita.setText(data);
        }

    /**
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, 1995, 1, 1);
        dialog.show();
    }
}
