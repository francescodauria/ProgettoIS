package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.R;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by User on 25/10/2017.
 */

public class ObiettiviAdapter extends ArrayAdapter<Obiettivo> {

    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private Obiettivo obiettivo;
    public ObiettiviAdapter(Context context, int resourceId, List<Obiettivo> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
        this.context=context;
    }



    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.tutor_az_custom_adapter_obiettivi_layout, null);
        }

        obiettivo= getItem(position);


        TextView nome = v.findViewById(R.id.lista_obiettivi_nome);
        TextView descrizione = v.findViewById(R.id.lista_obiettivi_descrizione);


        nome.setText(obiettivo.getNome());
        descrizione.setText(obiettivo.getDescrizione());



        return v;
    }


}
