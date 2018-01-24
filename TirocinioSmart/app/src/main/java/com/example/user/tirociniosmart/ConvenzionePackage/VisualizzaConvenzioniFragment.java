package com.example.user.tirociniosmart.ConvenzionePackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.DirettoreActivity;

import java.util.ArrayList;

/**
 * Created by User on 24/01/2018.
 */

public class VisualizzaConvenzioniFragment extends Fragment implements View.OnClickListener {

    private View view;
    public static ConvenzioneAdapter adapter;
    private ListView listView;
    private View mProgressView;
    private Direttore direttore;
    private ArrayList<Convenzione> listaConvenzioni;
    public static Context context;
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

        new LoadConvenzioni().execute(1);
        Bundle bundle = this.getArguments();
        direttore = (Direttore)bundle.getSerializable("direttore");
        adapter = new ConvenzioneAdapter(context, R.layout.direttore_custom_adapter_convenzioni_layout, new ArrayList<Convenzione>());


        View viewAdapter = inflater.inflate(R.layout.direttore_custom_adapter_convenzioni_layout, container, false);

        accettaConvenzione = viewAdapter.findViewById(R.id.accettaConvenzione);


        System.out.println(accettaConvenzione);

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

    @Override
    public void onClick(final View view) {
        System.out.println("ON CLICK");
        int position = Integer.parseInt(view.getTag().toString());
        System.out.println(VisualizzaConvenzioniFragment.adapter.toString());
        System.out.println(position);

        Convenzione c = adapter.getItem(position);
        System.out.println(c.getAzienda().getNome());
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:

                        int position = Integer.parseInt(view.getTag().toString());

                        //    Convenzione c = adapter.getItem(position);

                        //   adapter.remove(c);
                        //   listaConvenzioni.remove(c);
                        Toast.makeText(context.getApplicationContext(), "Elemento eliminato correttamente",Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(context.getApplicationContext(), "Operazione annullata",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };



        AlertDialog.Builder builder = new AlertDialog.Builder(VisualizzaConvenzioniFragment.context);
        builder.setMessage("Vuoi davvero accettare la convenzione?");
        builder.setPositiveButton("Si", dialogClickListener);
        builder.setNegativeButton("No", dialogClickListener);
        builder.show();

        return;

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

}
