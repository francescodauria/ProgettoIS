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

import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.ProgFormativoPackage.FirmaProgFormTutorAcFragment;
import com.example.user.tirociniosmart.R;
import com.example.user.tirociniosmart.UtenzaPackage.FragmentPackage.ModificaPasswordFragment;

import java.sql.SQLException;

public class TutorAcActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FragmentManager fm;
    public static MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
    private TutorAc tutorAc;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_ac_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getFragmentManager();

        tutorAc=(TutorAc)getIntent().getSerializableExtra("TUTORAC");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        pool = new MySQLConnectionPoolFreeSqlDB();

        Fragment f = new FirmaProgFormTutorAcFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("tutorac", tutorAc);
        f.setArguments(bundle);
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contenitoreFrammentiTutorAccademico,f,"richiesteTutorAc");
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

            if (id == R.id.richieste_tirocinio_TutorAccademico) {
                Fragment f = fm.findFragmentByTag("richiesteTutorAc");
                if (f == null) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tutorac",tutorAc);

                    f = new FirmaProgFormTutorAcFragment();
                    f.setArguments(bundle);
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.add(R.id.contenitoreFrammentiTutorAccademico, f, "richiesteTutorAc");
                    ft.addToBackStack(null);

                    ft.commit();
                } else {
                    FragmentTransaction ft = fm.beginTransaction();

                    ft.remove(f);
                    fm.popBackStack();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("tutorac",tutorAc);

                    f = new FirmaProgFormTutorAcFragment();
                    f.setArguments(bundle);
                    ft.add(R.id.contenitoreFrammentiTutorAccademico, f, "richiesteTutorAc");
                    ft.addToBackStack(null);

                    ft.commit();

                }
            } else if (id == R.id.account_TutorAccademico) {
            Fragment f = fm.findFragmentByTag("cambiaPassword");
            if (f == null) {
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAc.getClass().getSimpleName());
                bundle.putString("username",tutorAc.getUsername());
                bundle.putString("password",tutorAc.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.contenitoreFrammentiTutorAccademico, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();
            } else {
                FragmentTransaction ft = fm.beginTransaction();

                ft.remove(f);
                fm.popBackStack();
                Bundle bundle = new Bundle();
                bundle.putString("ruolo",tutorAc.getClass().getSimpleName());
                bundle.putString("username",tutorAc.getUsername());
                bundle.putString("password",tutorAc.getPassword());

                f = new ModificaPasswordFragment();
                f.setArguments(bundle);
                ft.add(R.id.contenitoreFrammentiTutorAccademico, f, "cambiaPassword");
                ft.addToBackStack(null);

                ft.commit();

            }
        }  else if (id == R.id.logoutTutorAccademico) {
            Intent intent = new Intent(TutorAcActivity.this,LoginActivity.class);
            ActivityCompat.finishAffinity(TutorAcActivity.this);

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

