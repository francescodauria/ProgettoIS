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
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;

import java.util.ArrayList;

/**
 * Created by User on 25/01/2018.
 */

public class FirmaProgFormDirettoreFragment extends Fragment {

    private ProgressBar progressBar;
    private static Context context;
    private View view;
    private ProgFormativoFirmaAdapter adapter;
    private ListView listView;
    private Direttore direttore;
    private LinearLayout linear;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        context = getActivity();
        view = inflater.inflate(R.layout.tutor_ac_fragment_firma_tirocinio_layout, container, false);
        progressBar = view.findViewById(R.id.tirocini_tutor_ac_progress);
        listView = view.findViewById(R.id.listViewTirociniTutorAc);
        Bundle b = getArguments();
        direttore = (Direttore) b.getSerializable("direttore");





        adapter = new ProgFormativoFirmaAdapter(context, R.layout.tutor_ac_custom_adapter_tirocini_layout, new ArrayList<ProgFormativo>());
        adapter.setOnItemClickListener(new ProgFormativoFirmaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View itemView, final int position) {
                //     System.out.println(position);
                System.out.println(itemView.getId());

                if (itemView.getId() == R.id.linearLayoutFirma) {
                    System.out.println("entrato nell'if");


                    linear = (LinearLayout)itemView;
                    System.out.println("Sto stampando nell'if" +linear);

                    System.out.println("firma button");
                    Intent intent = new Intent(getActivity(), FirmaActivity.class);
                    intent.putExtra("position", position);
                    startActivityForResult(intent, 1);
                } else {
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which) {
                                case DialogInterface.BUTTON_POSITIVE:

                                    ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
                                    NetworkInfo netInfo = cm.getActiveNetworkInfo();

                                    if (netInfo != null && netInfo.isConnected()) {
                                        if (itemView.getId() == R.id.accettaTutorAc) {
                                            ProgFormativo progetto = adapter.getItem(position);

                                            if(progetto.getFirmaDirettore()==null){
                                                Toast.makeText(getActivity().getApplicationContext(),"Attenzione, non è stata inserita alcuna firma",Toast.LENGTH_LONG).show();
                                            }
                                            else {
                                                progetto.setStato("ACCETTATO");
                                                new AccettaProgettoTask().execute(progetto);
                                            }
                                        } else if (itemView.getId() == R.id.rifiutaTutorAc) {
                                            ProgFormativo progetto = adapter.getItem(position);
                                            adapter.remove(progetto);
                                            progetto.setStato("RIFIUTATO");
                                            new RifiutaProgettoTask().execute(progetto);

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
            }
        });
        listView = (ListView) view.findViewById(R.id.listViewTirociniTutorAc);
        listView.setAdapter(adapter);

        new LoadIconTask().execute(1);
        return view;

    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                byte[] bitmapdata = data.getByteArrayExtra("bitmapdata");
                int position = data.getIntExtra("position", 1);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

                adapter.getItem(position).setFirmaDirettore(bitmap);

                adapter.setFirma(position,linear.getChildAt(1), bitmap);

                Toast.makeText(getActivity(), "Firma salvata correttamente", Toast.LENGTH_SHORT).show();


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Attenzione: firma non salvata", Toast.LENGTH_SHORT).show();

            }
        }

    }

    class LoadIconTask extends AsyncTask<Integer, Integer, ArrayList<ProgFormativo>> {

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected ArrayList<ProgFormativo> doInBackground(Integer... img_ids) {

            ArrayList<ProgFormativo> progetti = new ArrayList<>();

            ProgettoFormativoDAO.setConnectionPool(TutorAcActivity.pool);

            progetti = ProgettoFormativoDAO.findAllByDirettore(direttore);
            return progetti;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

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
    class RifiutaProgettoTask extends AsyncTask<ProgFormativo, Integer, String> {
        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected String doInBackground(ProgFormativo... progFormativo) {
            ArrayList<ProgFormativo> progetti = new ArrayList<>();
            ProgettoFormativoDAO.setConnectionPool(TutorAcActivity.pool);
            String s = ProgettoFormativoDAO.rifiutaProgettoFormativo(progFormativo[0]);
            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }
    }

    class AccettaProgettoTask extends AsyncTask<ProgFormativo, Integer, String> {
        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected String doInBackground(ProgFormativo... progFormativo) {
            ArrayList<ProgFormativo> progetti = new ArrayList<>();
            ProgettoFormativoDAO.setConnectionPool(TutorAcActivity.pool);
            String s = null;
            s= ProgettoFormativoDAO.insertFirmaByDirettore(progFormativo[0]);

            System.out.println(progFormativo[0].getFirmaTutorAcc());
            System.out.println(progFormativo[0].getStudente().getMatricola());
            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();
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