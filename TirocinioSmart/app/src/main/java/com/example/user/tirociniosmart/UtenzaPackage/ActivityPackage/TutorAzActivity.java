package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.tirociniosmart.ConvenzionePackage.AziendeAdapter;
import com.example.user.tirociniosmart.ConvenzionePackage.RichiediConvenzioneFragment;
import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.TutorAziendaleDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.ProgFormativoPackage.FirmaProgFormTutorAzFragment;
import com.example.user.tirociniosmart.ProgFormativoPackage.ObiettiviFragment;
import com.example.user.tirociniosmart.ProgFormativoPackage.VisualizzaStatoFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;
import java.util.ArrayList;

public class TutorAzActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private TutorAz tutorAz;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_az_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        new LoadTutor().execute(1);

        fm = getFragmentManager();
        boolean convenzione = getIntent().getBooleanExtra("CONVENZIONE",true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(tutorAz);
        if(convenzione) {
            navigationView.inflateMenu(R.menu.tutor_az_convenzionata_activity_body_navigation_view);
            Fragment f = new ObiettiviFragment();
            Bundle bundle = new Bundle();
            bundle.putString("idazienda",tutorAz.getAzienda().getId());
            f.setArguments(bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "visualizzaObiettivi");
            ft.addToBackStack(null);
            ft.commit();
        }
        else{
            navigationView.inflateMenu(R.menu.tutor_az_non_convenzionata_activity_body_navigation_view);
            Fragment f = new RichiediConvenzioneFragment();
            Bundle bundle = new Bundle();
            bundle.putString("idazienda",tutorAz.getAzienda().getId());
            f.setArguments(bundle);
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "statoRichiesta");
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed() {
        setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.richieste_tirocinio_TutorAziendale_convenzionato) {
            Fragment f = fm.findFragmentByTag("richiesteTirocinioTutorAz");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("cftutoraz",tutorAz.getCF());
                f = new FirmaProgFormTutorAzFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiesteTirocinioTutorAz");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("cftutoraz",tutorAz.getCF());
                f = new FirmaProgFormTutorAzFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiesteTirocinioTutorAz");
                ft.addToBackStack(null);

                ft.commit();

            }

        } else if(id==R.id.obiettivi_TutorAziendale_convenzionato) {

            Fragment f = fm.findFragmentByTag("visualizzaObiettivi");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("idazienda",tutorAz.getAzienda().getId());
                f = new ObiettiviFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "visualizzaObiettivi");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("idazienda",tutorAz.getAzienda().getId());
                f = new ObiettiviFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "visualizzaObiettivi");
                ft.addToBackStack(null);

                ft.commit();

            }

        }else if (id == R.id.richiesta_convenzione_TutorAziendale_non_convenzionato) {

            Fragment f = fm.findFragmentByTag("richiediConvenzione");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("idazienda",tutorAz.getAzienda().getId());

                f = new RichiediConvenzioneFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiediConvenzione");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("idazienda",tutorAz.getAzienda().getId());

                f = new RichiediConvenzioneFragment();
                f.setArguments(bundle);                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiediConvenzione");
                ft.addToBackStack(null);

                ft.commit();
            }
        } else if (id == R.id.account_TutorAziendale_convenzionato) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAz.getClass().getSimpleName());
                bundle.putString("username",tutorAz.getUsername());
                bundle.putString("password",tutorAz.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAz.getClass().getSimpleName());
                bundle.putString("username",tutorAz.getUsername());
                bundle.putString("password",tutorAz.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        } else if (id == R.id.account_TutorAziendale_non_convenzionato) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAz.getClass().getSimpleName());
                bundle.putString("username",tutorAz.getUsername());
                bundle.putString("password",tutorAz.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAz.getClass().getSimpleName());
                bundle.putString("username",tutorAz.getUsername());
                bundle.putString("password",tutorAz.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        }
        else if (id == R.id.logoutTutorAziendale_non_convenzionato) {
            Intent intent = new Intent(TutorAzActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(TutorAzActivity.this);

            startActivity(intent);

        }
        else if (id == R.id.logoutTutorAziendale_convenzionato) {
            Intent intent = new Intent(TutorAzActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(TutorAzActivity.this);

            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onStop(){

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onStop();
    }


    class LoadTutor extends AsyncTask<Integer, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(Integer... img_ids) {


            TutorAziendaleDAO.setConnectionPool(TutorAzActivity.pool);

            String cf= getIntent().getStringExtra("TUTORAZ");

            System.out.println(cf);

            tutorAz = TutorAziendaleDAO.findByCF(cf);


            String s = null;
            if(tutorAz==null)
                s="Errore di connessione,caricamento dati tutor non riuscito";
            return s;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String s) {
          //  showProgress(false);

            if(s!=null){
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

        }


    }




}

