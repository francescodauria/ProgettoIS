package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.R;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by User on 29/10/2017.
 */

public class CreaProgFormFragment extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener{

    private View view;
    private FragmentManager fm;
    private Button signature;
    private ImageView imageFirma;
    private TextView data_inizio;
    private TextView data_fine;
    private Date data_I;
    private Date data_F;
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
        Spinner azienda=(Spinner) view.findViewById(R.id.spinnerAzienda);
        List<String> aziende=new ArrayList<>();
        aziende.add("ITSYSTEM");
        azienda.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,aziende));
        data_inizio=view.findViewById(R.id.data_inizio);
        data_fine=view.findViewById(R.id.data_fine);
        data_inizio.setOnClickListener(this);
        data_fine.setOnClickListener(this);
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        if(data_inizio.getTag().equals("click"))
        {
            String data=""+i2+"-"+i1+"-"+i;
            data_I=new Date(i,i1,i2);
            data_inizio.setText(data);
            data_inizio.setTag("");
        }
        else if (data_fine.getTag().equals("click"))
        {
            String data=""+i2+"-"+i1+"-"+i;
            data_F=new Date(i,i1,i2);
            data_fine.setText(data);
            data_fine.setTag("");
        }
        else ;
    }

    @Override
    public void onClick(View view) {
        TextView text=(TextView)view;
        if(text.getId()==data_inizio.getId())
        {
            data_inizio.setTag("click");
            System.out.println("Click data_inizio");
        }
        else if(text.getId()==data_fine.getId())
        {
            data_fine.setTag("click");
            System.out.println("Click data_fine");
        }
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, 2018, 1, 1);
        dialog.show();
    }
}