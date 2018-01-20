package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;

import com.example.user.tirociniosmart.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class InserisciStudFragment extends android.app.Fragment {

    private View view;
    private CalendarView dataNascita;
    private Button insert;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=  inflater.inflate(R.layout.fragment_inserisci_stud, container, false);

        insert = view.findViewById(R.id.buttonInsertStudente);

        dataNascita = view.findViewById(R.id.dataNascitaStudente);


        insert.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){


            }


        });


        return view;
    }

}
