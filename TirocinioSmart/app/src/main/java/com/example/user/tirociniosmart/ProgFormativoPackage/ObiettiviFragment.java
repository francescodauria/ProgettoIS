package com.example.user.tirociniosmart.ProgFormativoPackage;

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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.AziendeAdapter;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

import java.util.ArrayList;

/**
 * Created by User on 21/10/2017.
 */

public class ObiettiviFragment extends Fragment {
    private ListView listView;
    private View mProgressView;

    private ObiettiviAdapter adapter;
    private View view;
    private Button button;
    private Context context;
    private EditText nome;
    private EditText descrizione;
    private Obiettivo obiettivo;
    private ArrayList<Obiettivo> lista;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.tutor_az_obiettivi_fragment, container, false);
        //  image = (ImageView) view.findViewById(R.id.elem_lista_logo);

        nome = view.findViewById(R.id.nomeObiettivo);
        descrizione = view.findViewById(R.id.descrizioneObiettivo);

        button = view.findViewById(R.id.bottoneObiettivo);
        mProgressView = view.findViewById(R.id.obiettivi_progress);
        showProgress(false);
        lista = new ArrayList<>();


        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                View focusView = null;

                if (TextUtils.isEmpty(nome.getText().toString())) {
                    nome.setError("Il campo nome non può essere vuoto");
                    focusView = nome;
                    focusView.requestFocus();
                } else if (TextUtils.isEmpty(descrizione.getText().toString())) {
                    descrizione.setError("Il campo descrizione non può essere vuoto");
                    focusView = descrizione;
                    focusView.requestFocus();
                } else {
                    ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
                    NetworkInfo netInfo = cm.getActiveNetworkInfo();

                    if(netInfo!=null && netInfo.isConnected())
                     new InsertTask().execute(1);

                    else
                        Toast.makeText(getActivity().getApplicationContext(), "Attenzione, connessione non disponibile", Toast.LENGTH_LONG).show();

                }
            }
        });
        listView = (ListView) view.findViewById(R.id.obiettiviListView);
        adapter = new ObiettiviAdapter(context, R.layout.tutor_az_custom_adapter_obiettivi_layout, new ArrayList<Obiettivo>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                obiettivo = (Obiettivo)listView.getItemAtPosition(i);

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
                                NetworkInfo netInfo = cm.getActiveNetworkInfo();

                                if(netInfo!=null && netInfo.isConnected())
                                    new RemoveTask().execute(1);

                                else
                                    Toast.makeText(getActivity().getApplicationContext(), "Attenzione, connessione non disponibile", Toast.LENGTH_LONG).show();

                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(getActivity().getApplicationContext(), "L'elemento non è stato eliminato",Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                };



                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Sei davvero sicuro di voler eliminare l'obiettivo selezionato?");
                builder.setPositiveButton("Si", dialogClickListener);
                builder.setNegativeButton("No", dialogClickListener);
                builder.show();



            }
        });


        new LoadTask().execute(1);

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

    class InsertTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {


            ObiettivoDAO.setConnectionPool(TutorAzActivity.pool);


            Azienda a = new Azienda("azienda1", null, null, null, null, null, null, null);
            obiettivo = new Obiettivo(nome.getText().toString(), descrizione.getText().toString(), a);

            String s = ObiettivoDAO.insert(obiettivo);

            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            if (s.equals("Esiste già un obiettivo con questo nome") || !s.equals("Connessione al database non presente")) {
                lista.add(obiettivo);
                adapter.add(obiettivo);

            }

            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();


            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }

    }


    class RemoveTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {


            ObiettivoDAO.setConnectionPool(TutorAzActivity.pool);

            String s = ObiettivoDAO.remove(obiettivo);


            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);
            if (s.equals("Rimozione avvenuta correttamente")) {

                lista.remove(obiettivo);

                    adapter.remove(obiettivo);

            }

            Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();


            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }

    }


    class LoadTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {


            ObiettivoDAO.setConnectionPool(TutorAzActivity.pool);

            lista = ObiettivoDAO.getAllObiettiviByAzienda("azienda1");


            String s = null;
            if(lista==null) {
                s= "Attenzione: connessione non disponibile";
            }

            else if(lista.size()==0){
                s= "Non vi è alcun obiettivo presente";
            }
            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);


            if(s!=null)
                Toast.makeText(getActivity().getApplicationContext(), s, Toast.LENGTH_LONG).show();

            else{
                for(Obiettivo o:lista){
                    adapter.add(o);
                }
            }

            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }

    }
}