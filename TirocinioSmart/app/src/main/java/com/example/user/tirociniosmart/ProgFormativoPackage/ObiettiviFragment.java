package com.example.user.tirociniosmart.ProgFormativoPackage;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.obiettivi_fragment, container, false);
      //  image = (ImageView) view.findViewById(R.id.elem_lista_logo);

        nome = view.findViewById(R.id.nomeObiettivo);
        descrizione = view.findViewById(R.id.descrizioneObiettivo);

        button = view.findViewById(R.id.bottoneObiettivo);
      //  mProgressView = view.findViewById(R.id.aziende_progress);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                new LoadIconTask().execute(1);
            }
        });
     //   new LoadIconTask().execute(1);

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

    class LoadIconTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            ObiettivoDAO.setConnectionPool(TutorAzActivity.pool);



            Azienda a =new Azienda("azienda1",null,null,null,null,null,null,null);
            Obiettivo obiettivo = new Obiettivo(nome.getText().toString(),descrizione.getText().toString(),a);


           String s = ObiettivoDAO.insert(obiettivo);

            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
            showProgress(false);


            Toast.makeText(getActivity().getApplicationContext(), s,Toast.LENGTH_LONG).show();


            //    image.setImageBitmap(BitmapFactory.decodeByteArray(result, 0, result.length));
        }


    }





}