package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
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

public class TestTutorAccademicoDAO {




    @Test
    public void Test_1insert(){

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);

        TutorAc tutorAc = new TutorAc("tutor1", "password", "tutorAc","111", "Luigi","Mascolo");

        String s = TutorAccademicoDAO.insert(tutorAc);

        assertEquals("Inserimento del tutor avvenuto correttamente", s);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_1findByUtente(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);
        Utente u = new Utente("tutor1", "password2");
        TutorAc tutorAc= null;
        try {
            tutorAc = TutorAccademicoDAO.findByUtente(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(tutorAc.getUsername(),"tutor1");
        assertEquals(tutorAc.getPassword(),"password2");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_2findByMatricola(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);
        TutorAc tutorAc= null;
            tutorAc = TutorAccademicoDAO.findByMatricola("111");

        assertEquals(tutorAc.getMatricola(),"111");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test_4getAllTutorAc(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);

        ArrayList<TutorAc> tutorAc = new ArrayList<>();

        tutorAc = TutorAccademicoDAO.getAllTutorAc();

        assertEquals(tutorAc.size(),3);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_3CambioPassword(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);
        Utente u = new Utente("tutor1", "password");
        String s = TutorAccademicoDAO.cambioPassword(u, "password2");
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
            rimuoviTutorAccademico();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviTutorAccademico() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Tutor_Accademico WHERE Username = ? AND Password = ?");
        stt.setString(1,"tutor1");
        stt.setString(2,"password2");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }

}
