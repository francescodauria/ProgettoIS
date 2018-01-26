package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.SegreteriaActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by User on 25/01/2018.
 */

public class ProgFormSegreteriaFragment extends Fragment {

    private ProgressBar progressBar;
    private static Context context;
    private View view;
    private ProgFormativoSegreteriaAdapter adapter;
    private ListView listView;
    private Segreteria segreteria;
    private LinearLayout linear;

    /**
     *
     * @param inflater
     * @param container
     * @param saveInstanceState
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.tutor_ac_fragment_firma_tirocinio_layout, container, false);
        progressBar = view.findViewById(R.id.tirocini_tutor_ac_progress);
        listView = view.findViewById(R.id.listViewTirociniTutorAc);
        Bundle b = getArguments();
        segreteria = (Segreteria) b.getSerializable("segreteria");





        adapter = new ProgFormativoSegreteriaAdapter(context, R.layout.segreteria_custom_adapter_tirocini_layout, new ArrayList<ProgFormativo>());
        adapter.setOnItemClickListener(new ProgFormativoSegreteriaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View itemView, final int position) {
                //     System.out.println(position);
                System.out.println(itemView.getId());


                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:

                                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                                    NetworkInfo netInfo = cm.getActiveNetworkInfo();

                                    if (netInfo != null && netInfo.isConnected()) {
                                        if (itemView.getId() == R.id.accettaSegreteria) {
                                            ProgFormativo progetto = adapter.getItem(position);
                                            Calendar now = Calendar.getInstance();

                                            Date date = new Date((now.get((Calendar.YEAR))-1900),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));
                                            progetto.setDataStipula(date);
                                            progetto.setStato("ACCETTATO");
                                            new CambiaStatoProgettoTask().execute(progetto);

                                        } else if (itemView.getId() == R.id.rifiutaSegreteria) {
                                            ProgFormativo progetto = adapter.getItem(position);
                                            progetto.setStato("RIFIUTATO");
                                            new CambiaStatoProgettoTask().execute(progetto);

                                        }
                                    } else {
                                        Toast.makeText(context.getApplicationContext(), "Attenzione, connessione ad internet assente", Toast.LENGTH_LONG).show();
                                    }

                                    break;
                                case DialogInterface.BUTTON_NEGATIVE:
                                    Toast.makeText(context.getApplicationContext(), "Operazione annullata", Toast.LENGTH_LONG).show();
                                    break;
                            }
                        }
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Vuoi davvero effettuare l'operazione?");
                    builder.setPositiveButton("Si", dialogClickListener);
                    builder.setNegativeButton("No", dialogClickListener);
                    builder.show();

            }
        });
        listView = (ListView) view.findViewById(R.id.listViewTirociniTutorAc);
        listView.setAdapter(adapter);

        new LoadIconTask().execute(1);
        return view;

    }



    class LoadIconTask extends AsyncTask<Integer, Integer, ArrayList<ProgFormativo>> {
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
        protected ArrayList<ProgFormativo> doInBackground(Integer... img_ids) {

            ArrayList<ProgFormativo> progetti= null;

            ProgettoFormativoDAO.setConnectionPool(SegreteriaActivity.pool);

            progetti = ProgettoFormativoDAO.findAllBySegreteria(segreteria);
            return progetti;

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
         * @param lista
         */
        @Override
        protected void onPostExecute(ArrayList<ProgFormativo> lista) {
            showProgress(false);


            if (lista == null)
                Toast.makeText(getActivity(), "Connessione al database non presente", Toast.LENGTH_LONG).show();
            else if (lista.size() == 0)
                Toast.makeText(getActivity(), "Non sono presenti richieste di tirocinio", Toast.LENGTH_LONG).show();
            else {


                for (ProgFormativo pr : lista) {
                    adapter.add(pr);

                }

            }
        }
    }
    class CambiaStatoProgettoTask extends AsyncTask<ProgFormativo, Integer, String> {
        /**
         *
          */
        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        /**
         *
         * @param progFormativo
         * @return
         */
        @Override
        protected String doInBackground(ProgFormativo... progFormativo) {
            ProgettoFormativoDAO.setConnectionPool(SegreteriaActivity.pool);
            String s = null;
            if(progFormativo[0].getStato().equals("ACCETTATO")){
                s = ProgettoFormativoDAO.acceptProgettoFormativoBySegreteria(progFormativo[0]);

            }
            else if(progFormativo[0].getStato().equals("RIFIUTATO")){
                s = ProgettoFormativoDAO.rifiutaProgettoFormativo(progFormativo[0]);
            }
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
            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
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