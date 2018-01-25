package com.example.user.tirociniosmart.ProgFormativoPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.ConvenzioneDAO;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Convenzione;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.DirettoreActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;

import java.io.ByteArrayOutputStream;
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
    private ArrayList<Azienda> aziende;
    private Spinner azienda;
    private Context context;
    private ProgressBar progressBar;
    private Spinner tutor;
    private ArrayList<TutorAc> tutors;
    private Spinner obiettivo;
    private TutorAz aziendale;
    private Azienda az;
    private Studente studente;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        context=getActivity();
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
        aziende=new ArrayList<>();
        azienda=(Spinner) view.findViewById(R.id.spinnerAzienda);
        tutor=(Spinner)view.findViewById(R.id.spinnerTutor);
        obiettivo=(Spinner)view.findViewById(R.id.spinnerObiettivi);
        progressBar=(ProgressBar)view.findViewById(R.id.new_request_progress);
        List<String> aziende=new ArrayList<>();

        Bundle bundle = getArguments();
        studente = (Studente)bundle.getSerializable("studente");
        azienda.setAdapter(new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,aziende));
        data_inizio=view.findViewById(R.id.data_inizio);
        data_fine=view.findViewById(R.id.data_fine);
        data_inizio.setOnClickListener(this);
        data_fine.setOnClickListener(this);
        new LoadAziende().execute(1);
        new LoadTutor().execute(1);
        azienda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                new LoadObiettivi().execute(1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Button button=view.findViewById(R.id.richiestaTirocinio);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data_inizio.getText().toString().equalsIgnoreCase("Seleziona data"))
                    Toast.makeText(context.getApplicationContext(),"Attenzione, selezionare data inizio tirocinio",Toast.LENGTH_LONG).show();


                if(imageFirma.getDrawable()==null)
                    Toast.makeText(context.getApplicationContext(),"Attenzione, non è stata inserita alcuna firma",Toast.LENGTH_LONG).show();
                new Insert().execute(1);
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        if(data_inizio.getTag().equals("click"))
        {
            String data=""+i2+"/"+(i1+1)+"/"+i;
            data_I=new Date(i,i1,i2);
            data_inizio.setText(data);
            data_inizio.setTag("");
        }
        else if (data_fine.getTag().equals("click"))
        {
            String data=""+i2+"/"+(i1+1)+"/"+i;
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
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, 2018, 0, 1);
        dialog.show();
    }
    class LoadAziende extends AsyncTask<Integer, Integer, ArrayList<Azienda> >{

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected ArrayList<Azienda> doInBackground(Integer... img_ids) {
            ArrayList<Azienda> az = new ArrayList<>();


            AziendaDAO.setConnectionPool(StudentActivity.pool);

            az = AziendaDAO.getAllAziendeConvenzionate();
            aziende=az;

            return aziende;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<Azienda> lista) {
            ArrayList<String> az=new ArrayList<>();

            if(lista!=null) {
                for(Azienda a:lista)
                {
                    az.add(a.getNome());
                }
                azienda.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,az));


            } else
                Toast.makeText(getActivity(),"Attenzione: connessione non disponibile", Toast.LENGTH_LONG).show();
                showProgress(false);
        }


    }
    class LoadTutor extends AsyncTask<Integer, Integer, ArrayList<TutorAc> >{

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected ArrayList<TutorAc> doInBackground(Integer... img_ids) {
            ArrayList<TutorAc> tutor = new ArrayList<>();


            TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
            tutor=TutorAccademicoDAO.getAllTutorAc();
            tutors=tutor;


            return tutor;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<TutorAc> lista) {
            ArrayList<String> az=new ArrayList<>();

            if(lista!=null) {
                for(TutorAc a:lista)
                {
                    az.add(a.getNome()+" "+a.getCognome());
                }
                tutor.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,az));
                showProgress(false);

            } else
                Toast.makeText(getActivity(),"Attenzione: connessione non disponibile", Toast.LENGTH_LONG).show();
                showProgress(false);
        }


    }
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);


            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
            progressBar.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressBar.setVisibility(show ? View.VISIBLE : View.GONE);
        }
    }
    class LoadObiettivi extends AsyncTask<Integer, Integer, ArrayList<Obiettivo> >{

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected ArrayList<Obiettivo> doInBackground(Integer... img_ids) {
            ArrayList<Obiettivo> ob = new ArrayList<>();


            ObiettivoDAO.setConnectionPool(StudentActivity.pool);
            String nome=(String)azienda.getSelectedItem();

            for(Azienda a:aziende)
            {
                if(a.getNome().equals(nome))
                {
                    az=a;
                }
            }

            ArrayList<Obiettivo> obiettivi=ObiettivoDAO.getAllObiettiviByAzienda(az.getId());

            return obiettivi;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(ArrayList<Obiettivo> lista) {
            ArrayList<String> az=new ArrayList<>();

            if(lista!=null) {
                for(Obiettivo a:lista)
                {
                    az.add(a.getNome());
                }
                obiettivo.setAdapter(new ArrayAdapter<String>(context,R.layout.support_simple_spinner_dropdown_item,az));
                showProgress(false);

            } else
                Toast.makeText(getActivity(),"Attenzione: connessione non disponibile", Toast.LENGTH_LONG).show();
                showProgress(false);
        }


    }
    class Insert extends AsyncTask<Integer, Integer, String >{

        @Override
        protected void onPreExecute() {
            showProgress(true);
        }

        @Override
        protected String doInBackground(Integer... img_ids) {
            String s="";
            String nomecognome="";
            nomecognome+=tutor.getSelectedItem().toString();
            System.out.println(nomecognome);
            String[] nomi=nomecognome.split(" ");
            System.out.println(nomi[0]);
            System.out.println(nomi[1]);
            TutorAc tutor=null;
            for (TutorAc t:tutors)
            {
                if(t.getNome().equals(nomi[0])&&t.getCognome().equals(nomi[1]))
                {
                    tutor=t;
                }
            }
            Drawable firma=imageFirma.getDrawable();
            Bitmap bitmap = ((BitmapDrawable)firma).getBitmap();
            aziendale= TutorAziendaleDAO.getTutorAzByAzienda(az);
            ArrayList<Direttore> direttori=(ArrayList<Direttore>)DirettoreDAO.getAllDirettori();
            Direttore dir=((ArrayList<Direttore>)DirettoreDAO.getAllDirettori()).get(0);
            System.out.println(direttori.size());
            System.out.println(dir);
            System.out.println(dir.getNome());
            System.out.println(dir.getMatricola());
            s=null;

            s=ProgettoFormativoDAO.insert(new ProgFormativo("IN CORSO",null,150,obiettivo.getSelectedItem().toString(),data_I,data_F,null,bitmap,studente,dir, tutor,aziendale));
            return s;
        }



        @Override
        protected void onPostExecute(String s) {
                Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
                showProgress(false);


        }


    }
}