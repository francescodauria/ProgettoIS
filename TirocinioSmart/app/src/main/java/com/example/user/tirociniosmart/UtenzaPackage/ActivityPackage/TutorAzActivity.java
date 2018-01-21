package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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

import com.example.user.tirociniosmart.ConvenzionePackage.RichiediConvenzioneFragment;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.ProgFormativoPackage.VisualizzaStatoFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class TutorAzActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    FragmentManager fm;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_az_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getFragmentManager();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        pool = new MySQLConnectionPoolFreeSqlDB();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String username = getIntent().getStringExtra("username");
        if(username.equals("conv"))
            navigationView.inflateMenu(R.menu.tutor_az_convenzionata_activity_body_navigation_view);
        else if(username.equals("nonconv")) {
            navigationView.inflateMenu(R.menu.tutor_az_non_convenzionata_activity_body_navigation_view);
            Fragment f = new RichiediConvenzioneFragment();
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
            // Handle the camera action
        } else if (id == R.id.richiesta_convenzione_TutorAziendale_non_convenzionato) {

            Fragment f = fm.findFragmentByTag("richiediConvenzione");
            if (f == null) {
                f = new RichiediConvenzioneFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiediConvenzione");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new RichiediConvenzioneFragment();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "richiediConvenzione");
                ft.addToBackStack(null);

                ft.commit();
            }
        } else if (id == R.id.account_TutorAziendale_convenzionato) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                f = new ModificaPasswordFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new ModificaPasswordFragment();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        } else if (id == R.id.account_TutorAziendale_non_convenzionato) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                f = new ModificaPasswordFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAziendale, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new ModificaPasswordFragment();
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

}

