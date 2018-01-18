package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.user.tirociniosmart.R;

/**
 * Created by User on 29/10/2017.
 */

public class CreaProgFormFragment extends Fragment {

    View view;
    FragmentManager fm;
    Button signature;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_nuova_richiesta_layout, container, false);


        //NextFragment nextFrag= new NextFragment();
        fm = getActivity().getFragmentManager();

        signature = (Button) view.findViewById(R.id.signature);
        signature.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(getActivity(), FirmaActivity.class);
                startActivityForResult(i, 1);
            }


        });


        return view;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = data.getStringExtra("firma");


                Toast.makeText(getActivity(), "Firma salvata correttamente "+result, Toast.LENGTH_SHORT).show();


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Attenzione: firma non salvata", Toast.LENGTH_SHORT).show();

            }
        }

    }
}