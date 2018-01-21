package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.AziendeAdapter;
import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 21/10/2017.
 */

public class VisualizzaStatoFragment extends Fragment {
    private ListView listView;

    private View mProgressView;
    private ProgFormativoAdapter adapter;
    private Context context;
    private View view;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context = activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View v = inflater.inflate(R.layout.student_fragment_stato_richiesta_layout, container, false);
        mProgressView = view.findViewById(R.id.tirocini_studente_progress);
        view = v;

        return v;

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

    class LoadIconTask extends AsyncTask<Integer, Integer, ArrayList<ProgFormativo>> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<ProgFormativo> doInBackground(Integer... img_ids) {
            ArrayList<ProgFormativo> progetti = new ArrayList<>();

            Studente s = new Studente(null, null, null, "0512103671", null, null, null, null, null, null, null, 0, null, null);
            AziendaDAO.setConnectionPool(StudentActivity.pool);
            try {
                progetti = ProgettoFormativoDAO.findAllByStudente(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return progetti;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<ProgFormativo> lista) {
            showProgress(false);
            adapter = new ProgFormativoAdapter(context, R.layout.student_custom_adapter_lista_richieste_layout, new ArrayList<ProgFormativo>());

            listView = (ListView) view.findViewById(R.id.listViewTirociniStudente);
            listView.setAdapter(adapter);

            for (ProgFormativo pr : lista) {
                adapter.add(pr);

            }


            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }


    }
}