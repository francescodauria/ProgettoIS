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

import java.sql.SQLException;
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

        LinearLayout layout = v.findViewById(R.id.linearLayoutRichiestaStudente);
        TextView dataInizio = v.findViewById(R.id.dataInizioRichiestaStudente);
        TextView dataFine = v.findViewById(R.id.datafineRichiestaStudente);
        //    TextView numeroOre = v.findViewById(R.id.numeroOreRichiestaStudente);
        TextView azienda = v.findViewById(R.id.nomaAziendaRichiestaStudente);
        TextView tutorAzienda = v.findViewById(R.id.tutorAziendaleRichiestaStudente);
        TextView tutorAccademico = v.findViewById(R.id.tutorAccademicoRichiestaStudente);
        TextView stato = v.findViewById(R.id.statoRichiestaStudente);
        ImageView firmaStudente = v.findViewById(R.id.firmaStudenteRichiestaStudente);
        ImageView firmaTutorAziendale = v.findViewById(R.id.firmaTutorAziendaleRichiestaStudente);
        ImageView firmaTutorAccademico = v.findViewById(R.id.firmaTutorAccademicoRichiestaStudente);
        ImageView firmaDirettore = v.findViewById(R.id.firmaDirettoreRichiestaStudente);
        ImageView logoAzienda = v.findViewById(R.id.logoAziendaRichiestaStudente);
        //    ImageView accettazioneSegreteria = v.findViewById(R.id.accettazioneSegreteriaRichiestaStudente);
        //    TextView  dataStipula = v.findViewById(R.id.dataStipulaRichiestaStudente);
        //    TextView obiettivi = v.findViewById(R.id.obiettiviRichiestaStudente);
        //    TextView motivazione = v.findViewById(R.id.motivazioneRichiestaStudente);




        azienda.setText(progettoFormativo.getTutorAz().getAzienda().getNome());

        tutorAccademico.setText(progettoFormativo.getTutorAc().getNome()+ " "+progettoFormativo.getTutorAc().getCognome() );

        tutorAzienda.setText(progettoFormativo.getTutorAz().getNome()+ " "+progettoFormativo.getTutorAz().getCognome() );

        logoAzienda.setImageBitmap(progettoFormativo.getTutorAz().getAzienda().getLogo());

        dataInizio.setText(progettoFormativo.getDataInizio().getDay()+"/"+progettoFormativo.getDataInizio().getMonth()+1+"/"+(progettoFormativo.getDataInizio().getYear()+1900));
        dataFine.setText(progettoFormativo.getDataFine().getDay()+"/"+progettoFormativo.getDataFine().getMonth()+1+"/"+(progettoFormativo.getDataFine().getYear()+1900));



        stato.setText(progettoFormativo.getStato());

        String statoString = progettoFormativo.getStato();

        if(statoString.equalsIgnoreCase("In corso")){
            layout.setBackgroundResource(R.drawable.student_incorso_state);
        }
        else if(statoString.equalsIgnoreCase("Accettato")){
            layout.setBackgroundResource(R.drawable.student_accepted_state);
        }

        else if(statoString.equalsIgnoreCase("Rifiutato")){
            layout.setBackgroundResource(R.drawable.student_accepted_state);
        }



        if(progettoFormativo.getFirmaStudente()==null)
            firmaStudente.setBackgroundResource(R.drawable.croce);
        else
            firmaStudente.setBackgroundResource(R.drawable.spunta);

        if(progettoFormativo.getGetFirmaTutorAz()==null)
            firmaTutorAziendale.setBackgroundResource(R.drawable.croce);
        else
            firmaTutorAziendale.setBackgroundResource(R.drawable.spunta);

        if(progettoFormativo.getFirmaTutorAcc()==null)
            firmaTutorAccademico.setBackgroundResource(R.drawable.croce);
        else
            firmaTutorAccademico.setBackgroundResource(R.drawable.spunta);



        if(progettoFormativo.getFirmaDirettore()==null) {
            firmaDirettore.setBackgroundResource(R.drawable.croce);

        }
        else {
            firmaDirettore.setBackgroundResource(R.drawable.spunta);

        }


        stato.setText(statoString);






        dataInizio.setTag(position);
        dataFine.setTag(position);
        azienda.setTag(position);
        tutorAzienda.setTag(position);
        tutorAccademico.setTag(position);
        stato.setTag(position);
        firmaStudente.setTag(position);
        firmaTutorAziendale.setTag(position);
        firmaTutorAccademico.setTag(position);
        firmaDirettore.setTag(position);

/*        numeroOre.setTag(position);
        accettazioneSegreteria.setTag(position);
        dataStipula.setTag(position);
        obiettivi.setTag(position);
        motivazione.setTag(position);*/
        return v;
    }

}
