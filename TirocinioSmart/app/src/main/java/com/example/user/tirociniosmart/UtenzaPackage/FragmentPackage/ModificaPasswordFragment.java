package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    private Button registra;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_modifica_password, container, false);

        registra = view.findViewById(R.id.modificaPassword);
        registra.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


            }

        });



        return view;

    }



}
