package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;

import java.util.ArrayList;

/**
 * Created by User on 25/01/2018.
 */

public class FirmaProgFormTutorAcFragment extends Fragment {

    private ProgressBar progressBar;
    private Context context;
    private View view;
    private ProgFormativoTutorAcAdapter adapter;
    private ListView listView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        context=getActivity();
        view = inflater.inflate(R.layout.tutor_ac_fragment_firma_tirocinio_layout, container, false);


        progressBar = view.findViewById(R.id.tirocini_tutor_ac_progress);
        listView = view.findViewById(R.id.listViewTirociniTutorAc);
        showProgress(true);
        Bundle b = getArguments();
        TutorAc tutorAc = (TutorAc)b.getSerializable("tutorac");

        return view;

    }




    class LoadIconTask extends AsyncTask<Integer, Integer, ArrayList<ProgFormativo>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<ProgFormativo> doInBackground(Integer... img_ids) {

            ArrayList<ProgFormativo> progetti = new ArrayList<>();

            ProgettoFormativoDAO.setConnectionPool(TutorAcActivity.pool);

            progetti = ProgettoFormativoDAO.findAllByStudente(studente);
            return progetti;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<ProgFormativo> lista) {
            showProgress(false);


            if(lista==null) Toast.makeText(getActivity(),"Connessione al database non presente",Toast.LENGTH_LONG).show();
            else {
                adapter = new ProgFormativoStudenteAdapter(context, R.layout.student_custom_adapter_lista_richieste_layout, new ArrayList<ProgFormativo>());

                listView = (ListView) view.findViewById(R.id.listViewTirociniStudente);
                listView.setAdapter(adapter);

                for (ProgFormativo pr : lista) {
                    adapter.add(pr);

                }



            }
        }


    }




    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
}
