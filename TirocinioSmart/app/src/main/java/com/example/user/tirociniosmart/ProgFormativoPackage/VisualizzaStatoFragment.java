package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.tirociniosmart.R;

/**
 * Created by User on 21/10/2017.
 */

public class VisualizzaStatoFragment extends Fragment {


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){

        View view = inflater.inflate(R.layout.fragment_stato_richiesta_layout, container, false);
        return view;

    }

}
