package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.RichiediConvenzioneFragment;
import com.example.user.tirociniosmart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class InserisciStudFragment extends android.app.Fragment {

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
    CalendarView dataNascita;


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
                else {

                }
                //    new LoadIconTask().execute(1);
            }


        });


        return view;
    }
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


    class LoadIconTask extends AsyncTask<Integer, Integer, String > {

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);

            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        }


    }

}
