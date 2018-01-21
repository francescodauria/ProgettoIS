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

import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.R;

import java.util.List;

/**
 * Created by User on 21/01/2018.
 */
public class ProgFormativoAdapter extends ArrayAdapter<ProgFormativo> {

    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private ProgFormativo progettoFormativo;

    public ProgFormativoAdapter(Context context, int resourceId, List<ProgFormativo> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.student_custom_adapter_lista_aziende_layout, null);
        }

        progettoFormativo= getItem(position);


        return v;
    }

}
