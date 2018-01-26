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

import com.example.user.tirociniosmart.EntityPackage.Convenzione;
import com.example.user.tirociniosmart.R;

import java.util.List;

/**
 * Created by User on 24/01/2018.
 */

public class ConvenzioneAdapter extends ArrayAdapter<Convenzione> {
    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private Convenzione c;
    private ImageView firmaView;
    private static OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(View itemView, int position);
    }

    /**
     *
      * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;
    }

    /**
     *
     * @param context
     * @param resourceId
     * @param objects
     */
    public ConvenzioneAdapter(Context context, int resourceId, List<Convenzione> objects) {
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
            v = inflater.inflate(R.layout.direttore_custom_adapter_convenzioni_layout, null);
        }

        c= getItem(position);

        TextView nome;
        Button descrizione;
        TextView email;
        ImageView logo;
        Button buttonAccetta;
        Button buttonRifiuta;
        nome = (TextView) v.findViewById(R.id.elem_lista_nome_convenzione);
        descrizione= (Button) v.findViewById(R.id.elem_lista_descrizione_convenzione);
        logo = (ImageView) v.findViewById(R.id.elem_lista_logo_convenzione);
        email = (TextView)v.findViewById(R.id.elem_lista_mail_convenzione);
        buttonAccetta = (Button)v.findViewById(R.id.accettaConvenzione);
        buttonRifiuta = (Button)v.findViewById(R.id.rifiutaConvenzione);

        logo.setImageBitmap(c.getAzienda().getLogo());

        nome.setText(c.getAzienda().getNome());
        email.setText("email: \n" + c.getAzienda().getEmail());

        buttonAccetta.setTag(position);
        buttonRifiuta.setTag(position);
        logo.setTag(position);
        nome.setTag(position);
        email.setTag(position);
        descrizione.setTag(position);

        buttonAccetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });

        buttonRifiuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });

        descrizione.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Convenzione convenzione = getItem(position);


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.student_custom_dialog_descrizione);
                ImageView image = (ImageView)dialog.findViewById(R.id.dialog_logo);
                image.setImageBitmap(convenzione.getAzienda().getLogo());
                TextView txt = (TextView)dialog.findViewById(R.id.dialog_descrizione);
                txt.setText(convenzione.getAzienda().getDescrizione());
                dialog.show();

            }

        });


        return v;
    }


}
