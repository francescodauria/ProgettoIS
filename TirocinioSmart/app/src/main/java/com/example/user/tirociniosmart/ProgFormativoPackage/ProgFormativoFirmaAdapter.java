package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAcActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;

import org.w3c.dom.Text;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 21/01/2018.
 */
public class ProgFormativoFirmaAdapter extends ArrayAdapter<ProgFormativo>  {

    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private ProgFormativo progettoFormativo;
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
    public ProgFormativoFirmaAdapter(Context context, int resourceId, List<ProgFormativo> objects) {
        super(context, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    /**
     *
     * @param position
     * @param v
     * @param parent
     * @return
     */
    public View getView(final int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.tutor_ac_custom_adapter_tirocini_layout, null);
        }
        progettoFormativo= getItem(position);


        TextView dataInizio = v.findViewById(R.id.dataInizioRichiestaTutorAcTextView);
        TextView dataFine = v.findViewById(R.id.datafineRichiestaTutorAcTextView);
        TextView azienda = v.findViewById(R.id.nomaAziendaRichiestaTutorAc);
        TextView tutorAzienda = v.findViewById(R.id.tutorAziendaleRichiestaTutorAc);
        TextView tutorAccademico = v.findViewById(R.id.tutorAccademicoRichiestaTutorAc);
        ImageView logoAzienda = v.findViewById(R.id.logoAziendaRichiestaTutorAc);
        TextView studente = v.findViewById(R.id.studenteRichiestaTutorAc);
        Button informazioni = v.findViewById(R.id.infoTutorAc);
        Button firma= v.findViewById(R.id.insertFirmaTutorAc);
        ImageView firmaView =v.findViewById(R.id.firmaTutorAc);
        LinearLayout linearLayout = v.findViewById(R.id.linearLayoutFirma);

        Button accetta = v.findViewById(R.id.accettaTutorAc);
        Button rifiuta = v.findViewById(R.id.rifiutaTutorAc);

        dataInizio.setText(progettoFormativo.getDataInizio().getDate()+"/"+progettoFormativo.getDataInizio().getMonth()+1+"/"+(progettoFormativo.getDataInizio().getYear()));
        dataFine.setText(progettoFormativo.getDataFine().getDate()+"/"+progettoFormativo.getDataFine().getMonth()+1+"/"+(progettoFormativo.getDataFine().getYear()));

        azienda.setText(progettoFormativo.getTutorAz().getAzienda().getNome());
        studente.setText(progettoFormativo.getStudente().getNome()+" " +progettoFormativo.getStudente().getCognome());
        tutorAccademico.setText(progettoFormativo.getTutorAc().getNome()+ " "+progettoFormativo.getTutorAc().getCognome() );

        tutorAzienda.setText(progettoFormativo.getTutorAz().getNome()+ " "+progettoFormativo.getTutorAz().getCognome() );

        logoAzienda.setImageBitmap(progettoFormativo.getTutorAz().getAzienda().getLogo());

        accetta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        rifiuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v,position);
            }
        });



        informazioni.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ProgFormativo p = getItem(position);


                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.tirocini_dialog_moreinfo);
                ImageView image = (ImageView)dialog.findViewById(R.id.dialog_logo_tirocini);
                image.setImageBitmap(p.getTutorAz().getAzienda().getLogo());
                TextView txt = (TextView)dialog.findViewById(R.id.dialog_obiettivo);
                TextView matricola = (TextView)dialog.findViewById(R.id.dialog_matricola);
                TextView dataNascita = (TextView)dialog.findViewById(R.id.dialog_data_nascita);
                matricola.setText(p.getStudente().getMatricola());
                dataNascita.setText(p.getStudente().getDataNascita().toString());
                txt.setText(p.getListaObiettivi());
                dialog.show();

            }

        });



        return v;
    }

    /**
     *
     * @param position
     * @param v
     * @param bitmap
     */
    public void setFirma(int position,View v,Bitmap bitmap){
        System.out.println(v);
        ImageView imageView = (ImageView)v;
        imageView.setImageBitmap(bitmap);

    }



}
