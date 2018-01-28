package com.example.user.tirociniosmart.DAOPackageTest;

import android.graphics.Bitmap;

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

public class TestAziendaDAO {


//Non ho un immagine png da compressare, il metodo funziona correttamente nell'ambiente android
/*    @Test
    public void Test_1insert(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        AziendaDAO.setConnectionPool(pool);

        Bitmap bm1 = Bitmap.createBitmap(100, 200, Bitmap.Config.ARGB_8888);
        Azienda az = new Azienda("aziendaId", "nome","mail","sede","descrizione",bm1,"numero",null);

        String s=null;
        try {
            s = AziendaDAO.insert(az);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(s,"Inserimento dell'azienda avvenuto con successo");

        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


    @Test
    public void Test_2findById(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        AziendaDAO.setConnectionPool(pool);
        Azienda az = null;
        try {
            az = AziendaDAO.findById("azienda3");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(az.getId(),"azienda3");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test_3getAllAziendeConvenzionate(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        AziendaDAO.setConnectionPool(pool);
        ArrayList<Azienda> lista = new ArrayList<>();

        lista = AziendaDAO.getAllAziendeConvenzionate();
        assertEquals(lista.size(),5);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Test_4getAllAziende(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        AziendaDAO.setConnectionPool(pool);
        ArrayList<Azienda> lista = new ArrayList<>();

        lista = AziendaDAO.getAllAziende();
        assertEquals(lista.size(),7);
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
            rimuoviAzienda();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviAzienda() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Azienda WHERE ID = ?");
        stt.setString(1,"aziendaId");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }







}
