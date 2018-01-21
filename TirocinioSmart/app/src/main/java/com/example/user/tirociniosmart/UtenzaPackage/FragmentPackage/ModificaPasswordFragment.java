package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.app.Fragment;
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
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.R;

import java.sql.SQLException;

/**
 * Created by User on 18/01/2018.
 */

public class ModificaPasswordFragment extends Fragment {
    private View view;

    private EditText nuovaPassword;
    private EditText ripetiPassword;
    private EditText vecchiaPassword;

    private Button registra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_modifica_password, container, false);


        nuovaPassword = view.findViewById(R.id.newPassword);
        ripetiPassword = view.findViewById(R.id.ripetiPassword);
        vecchiaPassword = view.findViewById(R.id.oldPassword);
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
            }

        });



        return view;

    }



}
