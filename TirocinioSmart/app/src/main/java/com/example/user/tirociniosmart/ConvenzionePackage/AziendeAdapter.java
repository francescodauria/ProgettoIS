package com.example.user.tirociniosmart.ConvenzionePackage;

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
import com.example.user.tirociniosmart.R;

import java.util.List;

/**
 * Created by User on 25/10/2017.
 */

public class AziendeAdapter extends ArrayAdapter<Azienda> {

    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private Azienda a;

    /**
     *
      * @param context
     * @param resourceId
     * @param objects
     */
    public AziendeAdapter(Context context, int resourceId, List<Azienda> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
        this.context=context;
    }


    /**
     *
     * @param position
     * @param v
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.student_custom_adapter_lista_aziende_layout, null);
        }

        a= getItem(position);


        TextView nome;
        Button descrizione;
        TextView email;
        ImageView logo;

        nome = (TextView) v.findViewById(R.id.elem_lista_nome);
        descrizione= (Button) v.findViewById(R.id.elem_lista_descrizione);
        logo = (ImageView) v.findViewById(R.id.elem_lista_logo);
        email = (TextView)v.findViewById(R.id.elem_lista_mail);

        logo.setImageBitmap(a.getLogo());

        //    logo.setImageDrawable(a.getImg());
        nome.setText(a.getNome());
        email.setText("email: \n" + a.getEmail());
        //  descrizione.setText(a.getDescrizione());

        logo.setTag(position);
        nome.setTag(position);
        email.setTag(position);
        descrizione.setTag(position);



        descrizione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Azienda az = getItem(position);


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.student_custom_dialog_descrizione);
                ImageView image = (ImageView)dialog.findViewById(R.id.dialog_logo);
                image.setImageBitmap(az.getLogo());
                TextView txt = (TextView)dialog.findViewById(R.id.dialog_descrizione);
                txt.setText(az.getDescrizione());
                //          DialogBoxDescrizione dialog = new DialogBoxDescrizione((Activity)context,az.getImg(), az.getDescrizione());
                dialog.show();

            }

        });


        return v;
    }


}
