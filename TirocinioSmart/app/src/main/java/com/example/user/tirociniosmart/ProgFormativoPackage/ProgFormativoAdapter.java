package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
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
            v = inflater.inflate(R.layout.student_custom_adapter_lista_richieste_layout, null);
        }
        progettoFormativo= getItem(position);

        TextView dataInizio = v.findViewById(R.id.dataInizioRichiestaStudente);
        TextView dataFine = v.findViewById(R.id.datafineRichiestaStudente);
        TextView numeroOre = v.findViewById(R.id.numeroOreRichiestaStudente);
        TextView azienda = v.findViewById(R.id.nomaAziendaRichiestaStudente);
        TextView tutorAzienda = v.findViewById(R.id.tutorAziendaleRichiestaStudente);
        TextView tutorAccademico = v.findViewById(R.id.tutorAccademicoRichiestaStudente);
        TextView stato = v.findViewById(R.id.statoRichiestaStudente);
        ImageView firmaStudente = v.findViewById(R.id.firmaStudenteRichiestaStudente);
        ImageView firmaTutorAziendale = v.findViewById(R.id.firmaTutorAziendaleRichiestaStudente);
        ImageView firmaTutorAccademico = v.findViewById(R.id.firmaTutorAccademicoRichiestaStudente);
        ImageView firmaDirettore = v.findViewById(R.id.firmaTutorAziendaleRichiestaStudente);
        ImageView accettazioneSegreteria = v.findViewById(R.id.accettazioneSegreteriaRichiestaStudente);
        TextView  dataStipula = v.findViewById(R.id.dataStipulaRichiestaStudente);
        TextView obiettivi = v.findViewById(R.id.obiettiviRichiestaStudente);
        TextView motivazione = v.findViewById(R.id.motivazioneRichiestaStudente);


        dataInizio.setText(progettoFormativo.getDataInizio().getDay()+"/"+progettoFormativo.getDataInizio().getMonth()+1+"/"+progettoFormativo.getDataInizio().getYear()+1900);
       dataFine.setText(progettoFormativo.getDataFine().getDay()+"/"+progettoFormativo.getDataFine().getMonth()+1+"/"+progettoFormativo.getDataFine().getYear()+1900);
       dataStipula.setText(progettoFormativo.getDataStipula().getDay()+"/"+progettoFormativo.getDataStipula().getMonth()+1+"/"+progettoFormativo.getDataStipula().getYear()+1900);
        numeroOre.setText(progettoFormativo.getNumeroOre()+"");
        stato.setText(progettoFormativo.getStato());
        if(progettoFormativo.getFirmaStudente()!=null)
            //firmaStudente = v.findViewById(R.drawable)


        dataInizio.setTag(position);
        dataFine.setTag(position);
        numeroOre.setTag(position);
        azienda.setTag(position);
        tutorAzienda.setTag(position);
        tutorAccademico.setTag(position);
        stato.setTag(position);
        firmaStudente.setTag(position);
        firmaTutorAziendale.setTag(position);
        firmaTutorAccademico.setTag(position);
        firmaDirettore.setTag(position);
        accettazioneSegreteria.setTag(position);
        dataStipula.setTag(position);
        obiettivi.setTag(position);
        motivazione.setTag(position);
        return v;
    }

}
