package com.example.user.tirociniosmart.ConvenzionePackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 21/10/2017.
 */

public class FragmentListaAziende extends Fragment {
    private ListView listView;
    private View mProgressView;

    boolean internet = true;
    ImageView image;
    AziendeAdapter adapter;
    View view;
    Context context;
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.student_fragment_lista_aziende_layout, container, false);
        image = (ImageView) view.findViewById(R.id.elem_lista_logo);

        mProgressView = view.findViewById(R.id.obiettivi_progress);
        showProgress(false);

        new LoadIconTask().execute(1);

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

    class LoadIconTask extends AsyncTask<Integer, Integer, ArrayList<Azienda>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<Azienda> doInBackground(Integer... img_ids) {
            ArrayList<Azienda> aziende = new ArrayList<>();


            AziendaDAO.setConnectionPool(StudentActivity.pool);

                aziende = AziendaDAO.getAllAziende();

            return aziende;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<Azienda> lista) {
            showProgress(false);

                if(lista!=null) {

                    adapter = new AziendeAdapter(context, R.layout.student_custom_adapter_lista_aziende_layout, new ArrayList<Azienda>());

                    listView = (ListView) view.findViewById(R.id.mylistview);
                    listView.setAdapter(adapter);

                    for (Azienda a : lista) {
                        adapter.add(a);

                    }


                } else
                    Toast.makeText(getActivity(),"Attenzione: connessione non disponibile", Toast.LENGTH_LONG).show();




            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }


    }





}