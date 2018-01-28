package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;


import java.sql.DriverManager;
import java.util.ArrayList;


/**
 * Created by User on 27/01/2018.
 */

public class TestDirettoreDAO {


    @BeforeClass
    public static void init() throws Exception{


        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("INSERT INTO Direttore_Dipartimento VALUES (?,?,?,?,?)");
        stt.setString(1,"0001");
        stt.setString(2,"Lucia");
        stt.setString(3,"Orazzo");
        stt.setString(4,"lorazzo");
        stt.setString(5,"password");

        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }

    @Test
    public void Test_1findByUtente(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        DirettoreDAO.setConnectionPool(pool);
        Utente u = new Utente("lorazzo", "password2");
        Direttore direttore= null;
        try {
            direttore = DirettoreDAO.findByUtente(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(direttore.getUsername(),"lorazzo");
        assertEquals(direttore.getPassword(),"password2");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test_4getAllDirettori(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        DirettoreDAO.setConnectionPool(pool);

        ArrayList<Direttore> dir = new ArrayList<>();

        dir = DirettoreDAO.getAllDirettori();

        assertEquals(dir.size(),2);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_3CambioPassword(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        DirettoreDAO.setConnectionPool(pool);
        Utente u = new Utente("lorazzo", "password");
           String s = DirettoreDAO.cambioPassword(u, "password2");
        assertEquals(s,"Cambio password avvenuto correttamente");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Test
    public void Test_2findByMatricola(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        DirettoreDAO.setConnectionPool(pool);
        Direttore direttore= null;
        try {
            direttore = DirettoreDAO.findByMatricola("0001");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(direttore.getMatricola(),"0001");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    @AfterClass
    public static void close()
    {

        try {
            rimuoviDirettore();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviDirettore() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Direttore_Dipartimento WHERE Username = ? AND Password = ?");
        stt.setString(1,"lorazzo");
        stt.setString(2,"password2");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }

}
