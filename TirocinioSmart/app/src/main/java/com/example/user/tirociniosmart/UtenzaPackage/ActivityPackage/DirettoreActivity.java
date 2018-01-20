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
import android.widget.Toast;

import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class DirettoreActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    private static MySQLConnectionPoolFreeSqlDB pool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direttore_activity);
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

        if (id == R.id.richieste_convenzione_direttore) {
            // Handle the camera action
        } else if (id == R.id.richieste_tirocinio_direttore) {

        } else if (id == R.id.account_Direttore) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                f = new ModificaPasswordFragment();
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiDirettore, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                f = new ModificaPasswordFragment();
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

    public void onStop(){

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onStop();
    }

}

