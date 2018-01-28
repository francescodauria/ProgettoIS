package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.ConvenzioneDAO;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Convenzione;
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
import java.sql.Date;
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

public class TestConvenzioneDAO {



    @Test
    public void Test_1insert(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ConvenzioneDAO.setConnectionPool(pool);

        Azienda az = new Azienda("azienda8", "nome","mail","sede","descrizione",null,"numero",null);
        Direttore dir = new Direttore(null, null, null,"000001", null, null);
        Convenzione convenzione = new Convenzione(az, new Date(2016,10,10), dir,"PROVA");
        convenzione.setId(1234);
        String s=null;
        try {
            s = ConvenzioneDAO.insert(convenzione);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(s,"Inserimento avvenuto correttamente");

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



   @Test
    public void Test_2cambiaStato(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ConvenzioneDAO.setConnectionPool(pool);
       Azienda az = new Azienda("azienda8", "nome","mail","sede","descrizione",null,"numero",null);
       Direttore dir = new Direttore(null, null, null,"000001", null, null);
       Convenzione convenzione = new Convenzione(az, new Date(2016,10,10), dir,"PROVA2");
       convenzione.setId(1234);
        String s = ConvenzioneDAO.cambiaStato(convenzione);
        assertEquals(s,"L'operazione è stata eseguita correttamente");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //find by direttore non si è potuta fare per il problema del bitmap

    @AfterClass
    public static void close()
    {

        try {
            rimuoviConvenzione();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviConvenzione() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Convenzione WHERE Stato = ?");
        stt.setString(1,"PROVA2");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }







}
