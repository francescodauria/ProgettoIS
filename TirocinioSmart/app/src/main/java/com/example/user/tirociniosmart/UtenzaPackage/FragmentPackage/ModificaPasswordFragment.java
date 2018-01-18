package com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.user.tirociniosmart.ConvenzionePackage.FragmentListaAziende;
import com.example.user.tirociniosmart.R;

/**
 * Created by User on 18/01/2018.
 */

public class ModificaPasswordFragment extends Fragment {
    View view;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.fragment_modifica_password, container, false);




        return view;

    }
}
