package com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.user.tirociniosmart.ConvenzionePackage.VisualizzaConvenzioniFragment;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.ProgFormativoPackage.FirmaProgFormDirettoreFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class DirettoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
    private Direttore direttore;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direttore_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getFragmentManager();
        direttore = (Direttore)getIntent().getSerializableExtra("DIRETTORE");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        pool = new MySQLConnectionPoolFreeSqlDB();

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

        if (id == R.id.richieste_convenzione_direttore) {
            Fragment f = fm.findFragmentByTag("richiesteConvenzione");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("direttore", direttore);

                f = new VisualizzaConvenzioniFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiDirettore, f, "richiesteConvenzione");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putSerializable("direttore",direttore);

                f = new VisualizzaConvenzioniFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiDirettore, f, "richiesteConvenzione");
                ft.addToBackStack(null);

                ft.commit();

            }
        } else if (id == R.id.richieste_tirocinio_direttore) {
            Fragment f = fm.findFragmentByTag("richiesteTirocinioDirettore");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("direttore",direttore);

                f = new FirmaProgFormDirettoreFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiDirettore, f, "richiesteTirocinioDirettore");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putSerializable("direttore",direttore);

                f = new FirmaProgFormDirettoreFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiDirettore, f, "richiesteTirocinioDirettore");
                ft.addToBackStack(null);

                ft.commit();

            }

        } else if (id == R.id.account_Direttore) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",direttore.getClass().getSimpleName());
                bundle.putString("username",direttore.getUsername());
                bundle.putString("password",direttore.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiDirettore, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",direttore.getClass().getSimpleName());
                bundle.putString("username",direttore.getUsername());
                bundle.putString("password",direttore.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiDirettore, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        }  else if (id == R.id.logoutDirettore) {
            Intent intent = new Intent(DirettoreActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(DirettoreActivity.this);

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

