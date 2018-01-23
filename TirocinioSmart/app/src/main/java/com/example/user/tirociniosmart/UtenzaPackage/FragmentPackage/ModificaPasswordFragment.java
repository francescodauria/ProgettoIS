package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.FragmentListaAziende;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.DAOPackage.StudenteDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.EntityPackage.Utente;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.DirettoreActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.SegreteriaActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

import java.sql.SQLException;

/**
 * Created by User on 18/01/2018.
 */

public class ModificaPasswordFragment extends Fragment {
    private View view;

    private EditText nuovaPassword;
    private EditText ripetiPassword;
    private EditText vecchiaPassword;
    private Utente utente;
    private Button registra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_modifica_password, container, false);


        nuovaPassword = view.findViewById(R.id.newPassword);
        ripetiPassword = view.findViewById(R.id.ripetiPassword);
        vecchiaPassword = view.findViewById(R.id.oldPassword);
        utente = StudentActivity.getStudente();
        registra = view.findViewById(R.id.modificaPassword);
        registra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                View focusView = null;

                if(TextUtils.isEmpty(nuovaPassword.getText().toString())) {
                    nuovaPassword.setError("Il campo nuova password non può essere vuoto");
                    focusView = nuovaPassword;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(ripetiPassword.getText().toString())) {
                    ripetiPassword.setError("Il campo ripeti password non può essere vuoto");
                    focusView = ripetiPassword;
                    focusView.requestFocus();
                }
                else if(TextUtils.isEmpty(vecchiaPassword.getText().toString())) {
                    focusView = vecchiaPassword;
                    focusView.requestFocus();
                    vecchiaPassword.setError("Il campo password precedente non può essere vuoto");
                }


                else{


                }
            }

        });



        return view;

    }

    class LoadIconTask extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Integer... img_ids) {

            if(utente instanceof Studente){
                StudenteDAO.setConnectionPool(StudentActivity.pool);
            }
            else if(utente instanceof TutorAz) {
                TutorAziendaleDAO.setConnectionPool(TutorAzActivity.pool);
            }
            else if(utente instanceof TutorAc) {
                TutorAccademicoDAO.setConnectionPool(TutorAcActivity.pool);
            }
            else if(utente instanceof Direttore) {
                DirettoreDAO.setConnectionPool(DirettoreActivity.pool);
            }
            else if(utente instanceof Segreteria) {
                SegreteriaDAO.setConnectionPool(SegreteriaActivity.pool);
            }



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
