package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.runners.MethodSorters;


import java.sql.DriverManager;
import java.util.ArrayList;


/**
 * Created by User on 27/01/2018.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class TestObiettivoDAO {



    @Test
    public void Test_1insert(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ObiettivoDAO.setConnectionPool(pool);

        Azienda az = new Azienda("azienda3", "nome","mail","sede","descrizione",null,"numero",null);

        Obiettivo obiettivo = new Obiettivo("obiettivo", "descrizioneObiettivo",az);
        String s=null;
        s = ObiettivoDAO.insert(obiettivo);

        assertEquals(s,"Inserimento avvenuto correttamente");

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Questo test di unità funziona in ambiente android, è stato provato e testato per ottenere tutti gli obiettivi di un'azienda.

    @Test
    public void Test_2getAllObiettivi(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ObiettivoDAO.setConnectionPool(pool);
        AziendaDAO.setConnectionPool(pool);
        ArrayList<Obiettivo> lista = new ArrayList<>();

        lista = ObiettivoDAO.getAllObiettiviByAzienda("azienda3");
        assertEquals(lista.size(),3);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_3remove(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ObiettivoDAO.setConnectionPool(pool);

        Azienda az = new Azienda("azienda3", "nome","mail","sede","descrizione",null,"numero",null);

        Obiettivo obiettivo = new Obiettivo("obiettivo", "descrizioneObiettivo",az);

        String s = ObiettivoDAO.remove(obiettivo);
        assertEquals(s,"Rimozione avvenuta correttamente");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }










}
