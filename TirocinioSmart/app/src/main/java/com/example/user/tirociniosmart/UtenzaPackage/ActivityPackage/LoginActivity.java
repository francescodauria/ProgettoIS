package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.ConvenzioneDAO;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.DAOPackage.StudenteDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Convenzione;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.EntityPackage.Utente;
import com.example.user.tirociniosmart.R;

import java.sql.SQLException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    private MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();;

    private UserLoginTask mAuthTask = null;
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }








    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }
          else if(TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;

        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute();
        }
    }

















    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 6;
    }

    /**
     * Shows the progress UI and hides the login form.
     */



















    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

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
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void registerAzienda(View view) {

            Intent intent = new Intent(LoginActivity.this, RegistrazioneTutorActivity.class);
            startActivity(intent);
    }


    public class UserLoginTask extends AsyncTask<Void, Void, Utente> {

        private final String mEmail;
        private final String mPassword;
        private boolean convenzione;

        public UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Utente doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Utente utente = new Utente(mEmail, mPassword);

                StudenteDAO.setConnectionPool(pool);
                TutorAccademicoDAO.setConnectionPool(pool);
                TutorAziendaleDAO.setConnectionPool(pool);
                SegreteriaDAO.setConnectionPool(pool);
                DirettoreDAO.setConnectionPool(pool);
                if (null != StudenteDAO.findByUtente(utente)) {
                    Utente studente = StudenteDAO.findByUtente(utente);
                    return studente;

                } else if (null != TutorAziendaleDAO.findByUtente(utente)) {
                    Utente tutor = TutorAziendaleDAO.findByUtente(utente);
                    Convenzione c=new Convenzione(((TutorAz)tutor).getAzienda(), null,null,"ACCETTATO");
                    ConvenzioneDAO.setConnectionPool(pool);
                    if(!ConvenzioneDAO.checkConvenzione(c,"ACCETTATO")) {
                        convenzione=true;
                    } else {
                        convenzione=false;
                    }
                    return tutor;

                } else if (null != TutorAccademicoDAO.findByUtente(utente)) {
                    Utente tutor = TutorAccademicoDAO.findByUtente(utente);
                    return tutor;

                } else if (null != SegreteriaDAO.findByUtente(utente)) {
                    Utente segr = SegreteriaDAO.findByUtente(utente);
                    return segr;

                } else if (null != DirettoreDAO.findByUtente(utente)) {
                    Utente dir = DirettoreDAO.findByUtente(utente);
                    return dir;

                }

            }
            catch (SQLException e)
             {
             }



            // TODO: register the new account here.
            return null;
        }






        @Override
        protected void onPostExecute(Utente utente) {
            mAuthTask = null;
            showProgress(false);
            Intent i = null;

            if (null!=utente) {
                if(utente instanceof Studente)
                {
                    i=new Intent(getApplication(),StudentActivity.class);

                    i.putExtra("STUDENTE",(Studente)utente);
                }
                if(utente instanceof TutorAc)
                {
                    i=new Intent(getApplication(),TutorAcActivity.class);

                    i.putExtra("TUTORAC",(TutorAc)utente);
                }
                if(utente instanceof TutorAz)
                {
                    i=new Intent(getApplication(),TutorAzActivity.class);

                    TutorAz tutorAz =(TutorAz)utente;

                    i.putExtra("TUTORAZ",tutorAz.getCF());
                    i.putExtra("CONVENZIONE",convenzione);

                }
                if(utente instanceof Segreteria)
                {
                    i=new Intent(getApplication(),SegreteriaActivity.class);

                    i.putExtra("SEGRETERIA",(Segreteria)utente);
                }
                if(utente instanceof Direttore)
                {
                    i=new Intent(getApplication(),DirettoreActivity.class);

                    i.putExtra("DIRETTORE",(Direttore)utente);
                }





                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );



                startActivity(i);
                ActivityCompat.finishAffinity(LoginActivity.this);

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }





        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }



    public void onStop(){

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onStop();
    }

}

