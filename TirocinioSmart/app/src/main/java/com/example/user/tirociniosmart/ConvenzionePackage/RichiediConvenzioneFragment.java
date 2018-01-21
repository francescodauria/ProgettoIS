package com.example.user.tirociniosmart.ConvenzionePackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.FragmentListaAziende;
import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.ConvenzioneDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Convenzione;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 18/01/2018.
 */

public class RichiediConvenzioneFragment extends Fragment {
    private View view;
    private View mProgressView;

    private Button richiestaConvenzione;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_richiedi_convenzione, container, false);

        mProgressView = view.findViewById(R.id.invio_convenzione_progress);
        showProgress(false);
        richiestaConvenzione = view.findViewById(R.id.richiestaConvenzioneButton);
        richiestaConvenzione.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                new LoadIconTask().execute(1);

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



    class LoadIconTask extends AsyncTask<Integer, Integer, String >{

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            Direttore dir = new Direttore("fferrucci","password","direttore", "000001","Filomena", "Ferrucci");
            Azienda az = new Azienda("azienda2","ITSystem",null,null,null,null,null,null);
            Convenzione c = new Convenzione(az,null,dir, "IN CORSO");

            ConvenzioneDAO.setConnectionPool(TutorAzActivity.pool);
            String s = null;
            try {
                s = ConvenzioneDAO.insert(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return s;
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
