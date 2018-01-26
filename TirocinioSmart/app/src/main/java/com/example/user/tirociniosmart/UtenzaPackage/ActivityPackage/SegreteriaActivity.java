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

import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.ProgFormativoPackage.ProgFormSegreteriaFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.InserisciStudFragment;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class SegreteriaActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
    private Segreteria segreteria;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.segreteria_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getFragmentManager();
        segreteria = (Segreteria)getIntent().getSerializableExtra("SEGRETERIA");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        Fragment f = new InserisciStudFragment();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contenitoreFrammentiSegreteria, f, "inserisciStud");
        ft.addToBackStack(null);

        ft.commit();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     *
     */
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

    /**
     *
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.insert_studente) {

            Fragment f = fm.findFragmentByTag("inserisciStud");
            if (f == null) {
                f = new InserisciStudFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "inserisciStud");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new InserisciStudFragment();
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "inserisciStud");
                ft.addToBackStack(null);

                ft.commit();

            }


        } else if (id == R.id.richieste_tirocinio_segreteria) {
            Fragment f = fm.findFragmentByTag("richiesteTirocinioSegreteria");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("segreteria",segreteria);
                f = new ProgFormSegreteriaFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "richiesteTirocinioSegreteria");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putSerializable("segreteria",segreteria);
                f = new ProgFormSegreteriaFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "richiesteTirocinioSegreteria");
                ft.addToBackStack(null);

                ft.commit();

            }
        } else if (id == R.id.account_Segreteria) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",segreteria.getClass().getSimpleName());
                bundle.putString("username",segreteria.getUsername());
                bundle.putString("password",segreteria.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                System.out.println(segreteria.getClass().getSimpleName());
                bundle.putString("ruolo",segreteria.getClass().getSimpleName());
                bundle.putString("username",segreteria.getUsername());
                bundle.putString("password",segreteria.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiSegreteria, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        }  else if (id == R.id.logoutSegreteria) {
            Intent intent = new Intent(SegreteriaActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(SegreteriaActivity.this);

            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     *
     */
    public void onStop(){

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onStop();
    }
}

