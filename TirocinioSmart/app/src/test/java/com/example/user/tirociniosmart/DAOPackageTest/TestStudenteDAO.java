package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.DAOPackage.StudenteDAO;
import com.example.user.tirociniosmart.DAOPackage.TutorAccademicoDAO;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import static org.junit.Assert.*;

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
public class TestStudenteDAO {



    @Test
    public void Test_1insert(){

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        StudenteDAO.setConnectionPool(pool);

        Studente studente = new Studente("username","password","ruolo","matricola", "Gianluca","cognome","cf","mail", "indirizzo", "luogoNascita", new Date(1997,3,9), 3, "numero", null);


            String s = StudenteDAO.insert(studente);

        assertEquals("Inserimento avvenuto correttamente", s);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_2findByUtente(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        TutorAccademicoDAO.setConnectionPool(pool);
        Utente u = new Utente("username", "password");
        Studente studente = null;
        try {
            studente = StudenteDAO.findByUtente(u);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assertEquals(studente.getUsername(),"username");
        assertEquals(studente.getPassword(),"password");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_3findByMatricola(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        StudenteDAO.setConnectionPool(pool);
        Studente studente = null;
        try {
            studente = StudenteDAO.findByMatricola("matricola");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertEquals(studente.getNome(),"Gianluca");
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void Test_4CambioPassword(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        StudenteDAO.setConnectionPool(pool);
        Utente u = new Utente("username", "password");
        String s = StudenteDAO.cambioPassword(u, "password2");
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
            rimuoviStudente();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void rimuoviStudente() throws Exception{

        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        Connection newConnection = null;
        newConnection = (Connection) pool.getConnection();
        newConnection.setAutoCommit(false);


        PreparedStatement stt = newConnection.prepareStatement("DELETE FROM Studente WHERE Username = ? AND Password = ?");
        stt.setString(1,"username");
        stt.setString(2,"password2");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();
        newConnection.close();

        pool.closeAllConnection();

    }

}
