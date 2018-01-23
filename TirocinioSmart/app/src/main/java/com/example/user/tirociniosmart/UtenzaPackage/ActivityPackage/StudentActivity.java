package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.ConvenzionePackage.FragmentListaAziende;
import com.example.user.tirociniosmart.ProgFormativoPackage.CreaProgFormFragment;
import com.example.user.tirociniosmart.ProgFormativoPackage.VisualizzaStatoFragment;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class StudentActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fm;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
    private Studente studente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        studente=(Studente)getIntent().getSerializableExtra("STUDENTE");
        //   String password = getIntent().getStringExtra("password");
        //   String email = getIntent().getStringExtra("email");
        //   Toast.makeText(getApplicationContext(),"ciao " + prova, Toast.LENGTH_LONG).show();

        fm = getFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Fragment f = new VisualizzaStatoFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.contenitoreFrammentiStudente, f, "statoRichiesta");
        ft.addToBackStack(null);
        ft.commit();


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


        if (id == R.id.state_request) {
            Fragment f = fm.findFragmentByTag("statoRichiesta");

            if (f == null) {
                f = new VisualizzaStatoFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiStudente, f, "statoRichiesta");
                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(f);
                f = new VisualizzaStatoFragment();

                ft.add(R.id.contenitoreFrammentiStudente, f, "statoRichiesta");
                ft.commit();
            }


        } else if (id == R.id.list_agency) {
            Fragment f = fm.findFragmentByTag("listaAziende");
            if (f == null) {
                f = new FragmentListaAziende();

                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiStudente, f, "listaAziende");

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                f = new FragmentListaAziende();
                ft.add(R.id.contenitoreFrammentiStudente, f, "listaAziende");
                ft.commit();
            }


        } else if (id == R.id.nav_manage) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {

                f = new ModificaPasswordFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiStudente, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new ModificaPasswordFragment();

                ft.add(R.id.contenitoreFrammentiStudente, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            }

        }  else if (id == R.id.nav_send) {
            Fragment f = fm.findFragmentByTag("nuovaRichiesta");
            if (f == null) {
                f = new CreaProgFormFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiStudente, f, "nuovaRichiesta");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();

                f = new CreaProgFormFragment();
                ft.add(R.id.contenitoreFrammentiStudente, f, "nuovaRichiesta");
                ft.addToBackStack(null);

                ft.commit();
            }
        }
            else if(id==R.id.logout){
                Intent intent = new Intent(StudentActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(StudentActivity.this);

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


}