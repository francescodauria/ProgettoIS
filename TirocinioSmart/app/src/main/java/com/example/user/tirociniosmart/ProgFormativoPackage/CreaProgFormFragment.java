package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.tirociniosmart.R;

/**
 * Created by User on 29/10/2017.
 */

public class CreaProgFormFragment extends Fragment {

    private View view;
    private FragmentManager fm;
    private Button signature;
    private ImageView imageFirma;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        view = inflater.inflate(R.layout.student_fragment_nuova_richiesta_layout, container, false);


        //NextFragment nextFrag= new NextFragment();
        fm = getActivity().getFragmentManager();
        imageFirma = view.findViewById(R.id.imageFirma);
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
                byte[] bitmapdata = data.getByteArrayExtra("bitmapdata");

                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);

                imageFirma.setImageBitmap(bitmap);

                Toast.makeText(getActivity(), "Firma salvata correttamente", Toast.LENGTH_SHORT).show();


            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getActivity(), "Attenzione: firma non salvata", Toast.LENGTH_SHORT).show();

            }
        }

    }
}