package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.DAOPackage.StudenteDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Utente;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.DirettoreActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.SegreteriaActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

/**
 * Created by User on 18/01/2018.
 */

public class ModificaPasswordFragment extends Fragment {
    private View view;

    private EditText nuovaPassword;
    private EditText ripetiPassword;
    private EditText vecchiaPassword;
    private Utente utente;
    private View mProgressView;
    private Button registra;
    String ruolo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_modifica_password, container, false);

        mProgressView = view.findViewById(R.id.change_pass_progress);

        nuovaPassword = view.findViewById(R.id.newPassword);
        ripetiPassword = view.findViewById(R.id.ripetiPassword);
        vecchiaPassword = view.findViewById(R.id.oldPassword);

        Bundle bundle = this.getArguments();
        ruolo = bundle.getString("ruolo");
        String username = bundle.getString("username");
        String password = bundle.getString("password");

        utente= new Utente(username,password);


        registra = view.findViewById(R.id.modificaPassword);
        registra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                View focusView = null;

                if (TextUtils.isEmpty(nuovaPassword.getText().toString())) {
                    nuovaPassword.setError("Il campo nuova password non può essere vuoto");
                    focusView = nuovaPassword;
                    focusView.requestFocus();
                } else if (TextUtils.isEmpty(ripetiPassword.getText().toString())) {
                    ripetiPassword.setError("Il campo ripeti password non può essere vuoto");
                    focusView = ripetiPassword;
                    focusView.requestFocus();
                } else if (TextUtils.isEmpty(vecchiaPassword.getText().toString())) {
                    focusView = vecchiaPassword;
                    focusView.requestFocus();
                    vecchiaPassword.setError("Il campo password precedente non può essere vuoto");
                } else if (nuovaPassword.getText().toString().length()<7) {
                    focusView = nuovaPassword;
                    focusView.requestFocus();
                    nuovaPassword.setError("La nuova password deve essere almeno di 7 caratteri");
                } else if (!nuovaPassword.getText().toString().equals(ripetiPassword.getText().toString())) {
                    focusView = ripetiPassword;
                    focusView.requestFocus();
                    ripetiPassword.setError("Il campo ripeti password deve coincidere con nuova password");

                } else if (!utente.getPassword().equals(vecchiaPassword.getText().toString())) {
                    focusView = vecchiaPassword;
                    focusView.requestFocus();
                    vecchiaPassword.setError("La vecchia password inserita non è corretta");
                } else {
                    new ModificaTask().execute(1);
                }


            }
        });


        return view;

    }

    class ModificaTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            String s = null;
            if(ruolo.equals("Studente")){
                StudenteDAO.setConnectionPool(StudentActivity.pool);
                s = StudenteDAO.cambioPassword(utente, nuovaPassword.getText().toString());
            }
            else if(ruolo.equals("TutorAz")) {
                TutorAziendaleDAO.setConnectionPool(TutorAzActivity.pool);
                s = TutorAziendaleDAO.cambioPassword(utente, nuovaPassword.getText().toString());
            }
            else if(ruolo.equals("TutorAc")) {
                TutorAccademicoDAO.setConnectionPool(TutorAcActivity.pool);
                s=TutorAccademicoDAO.cambioPassword(utente,nuovaPassword.getText().toString());
            }
            else if(ruolo.equals("Direttore")) {
                DirettoreDAO.setConnectionPool(DirettoreActivity.pool);
                s=DirettoreDAO.cambioPassword(utente,nuovaPassword.getText().toString());
            }
            else if(ruolo.equals("Segreteria")) {
                SegreteriaDAO.setConnectionPool(SegreteriaActivity.pool);
                s=SegreteriaDAO.cambioPassword(utente,nuovaPassword.getText().toString());
            }




            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
                showProgress(false);
                if(s.equals("Cambio password avvenuto correttamente")){
                    utente.setPassword(nuovaPassword.getText().toString());
                }
                Toast.makeText(getActivity(),s, Toast.LENGTH_LONG).show();




        }


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

}
