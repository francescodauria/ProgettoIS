package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
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


/**
 * Created by User on 27/01/2018.
 */

public class TestSegreteriaDAO {


    @BeforeClass
    public static void init() throws Exception{


        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("INSERT INTO Segreteria VALUES (?,?)");
        stt.setString(2,"Password");
        stt.setString(1,"Utente");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }

    @Test
    public void Test1_findByUtente(){

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();

        SegreteriaDAO.setConnectionPool(pool);

        Utente u = new Utente("Utente", "Password");

        Segreteria segreteria= null;
        try {
             segreteria = SegreteriaDAO.findByUtente(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(segreteria.getUsername(),"Utente");
        assertEquals(segreteria.getPassword(),"Password");


            String s = SegreteriaDAO.cambioPassword(u, "password");

        assertEquals(s,"Cambio password avvenuto correttamente");

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
            rimuoviSegreteria();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviSegreteria() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Segreteria WHERE Username = ? AND Password = ?");
        stt.setString(1,"Utente");
        stt.setString(2,"password");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

            pool.closeAllConnection();

    }

}
