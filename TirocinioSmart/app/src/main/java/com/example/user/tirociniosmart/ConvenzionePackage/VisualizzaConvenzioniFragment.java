package com.example.user.tirociniosmart.ConvenzionePackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.ConvenzioneDAO;
import com.example.user.tirociniosmart.EntityPackage.Convenzione;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.ProgFormativoPackage.ObiettiviFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.DirettoreActivity;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by User on 24/01/2018.
 */

public class VisualizzaConvenzioniFragment extends Fragment {

    private View view;
    public ConvenzioneAdapter adapter;
    private ListView listView;
    private View mProgressView;
    private Direttore direttore;
    private ArrayList<Convenzione> listaConvenzioni;
    public Context context;
    private Button rifiutaConvenzione;
    private Button accettaConvenzione;
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.direttore_fragment_firma_convenzione, container, false);

        mProgressView = view.findViewById(R.id.richieste_convenzioni_progress);

        listView = view.findViewById(R.id.richiesteConvenzioniLista);
        Bundle bundle = this.getArguments();
        direttore = (Direttore)bundle.getSerializable("direttore");

        adapter = new ConvenzioneAdapter(context, R.layout.direttore_custom_adapter_convenzioni_layout, new ArrayList<Convenzione>());

        adapter.setOnItemClickListener(new ConvenzioneAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final View itemView, final int position) {
                //     System.out.println(position);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
                                NetworkInfo netInfo = cm.getActiveNetworkInfo();

                                if(netInfo!=null && netInfo.isConnected()){

                                    Convenzione c = adapter.getItem(position);

                                    Calendar now = Calendar.getInstance();

                                    Date date = new Date((now.get((Calendar.YEAR))-1900),now.get(Calendar.MONTH),now.get(Calendar.DAY_OF_MONTH));

                                    if(itemView.getId() == R.id.accettaConvenzione) {
                                        c.setDataStipula(date);
                                        c.setStato("ACCETTATO");
                                    }
                                    else if(itemView.getId() == R.id.rifiutaConvenzione){
                                        c.setStato("RIFIUTATO");

                                    }
                                    new CambiaStato().execute(c);
                                }

                                else
                                {
                                    Toast.makeText(context.getApplicationContext(), "Attenzione, connessione ad internet assente",Toast.LENGTH_LONG).show();

                                }

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(context.getApplicationContext(), "Operazione annullata",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Vuoi davvero accettare la convenzione?");
                builder.setPositiveButton("Si", dialogClickListener);
                builder.setNegativeButton("No", dialogClickListener);
                builder.show();
            }
        });
        new LoadConvenzioni().execute(1);

        return view;

    }

    private void showProgress(final boolean show) {

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







    class LoadConvenzioni extends AsyncTask<Integer, Integer, ArrayList<Convenzione>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<Convenzione> doInBackground(Integer... img_ids) {
            ArrayList<Convenzione> aziende = new ArrayList<>();


            ConvenzioneDAO.setConnectionPool(DirettoreActivity.pool);

            listaConvenzioni = ConvenzioneDAO.findByDirettore(direttore.getMatricola());

            for(Convenzione c:listaConvenzioni) {
                System.out.println("Id azienda " + c.getAzienda().getId());
                System.out.println("Id convenzione " + c.getId());
            }
            return listaConvenzioni;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<Convenzione> lista) {
            showProgress(false);

            if(lista!=null) {
                listView.setAdapter(adapter);

                for (Convenzione c : lista) {
                    System.out.println(c.getAzienda().getNome());
                    adapter.add(c);

                }


            } else
                Toast.makeText(getActivity(),"Attenzione: connessione non disponibile", Toast.LENGTH_LONG).show();

        }


    }


    class CambiaStato extends AsyncTask<Convenzione, Convenzione, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Convenzione... c) {


            Convenzione convenzione = c[0];
            String stato;
            ConvenzioneDAO.setConnectionPool(DirettoreActivity.pool);

            stato = ConvenzioneDAO.cambiaStato(convenzione);

            if(stato.equals("L'operazione è stata eseguita correttamente"))
                publishProgress(c[0]);
            return stato;
        }

        @Override
        protected void onProgressUpdate(Convenzione... c) {

            listaConvenzioni.remove(c[0]);
            adapter.remove(c[0]);

        }

        @Override
        protected void onPostExecute(String s) {


            Toast.makeText(context.getApplicationContext(),s, Toast.LENGTH_LONG).show();

        }


    }





}
