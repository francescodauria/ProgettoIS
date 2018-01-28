package com.example.user.tirociniosmart.DAOPackageTest;

import com.example.user.tirociniosmart.DAOPackage.AziendaDAO;
import com.example.user.tirociniosmart.DAOPackage.DirettoreDAO;
import com.example.user.tirociniosmart.DAOPackage.MySQLConnectionPoolFreeSqlDB;
import com.example.user.tirociniosmart.DAOPackage.ObiettivoDAO;
import com.example.user.tirociniosmart.DAOPackage.ProgettoFormativoDAO;
import com.example.user.tirociniosmart.DAOPackage.SegreteriaDAO;
import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
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

public class TestProgettoFormativoDAO {



/*
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
*/


    //Questo test di unità funziona in ambiente android, è stato provato e testato per ottenere tutti gli obiettivi di un'azienda.

    @Test
    public void Test_1findAllByStudente(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ProgettoFormativoDAO.setConnectionPool(pool);
        Studente studente = new Studente("username","password","ruolo","0512103671", "Gianluca","cognome","cf","mail", "indirizzo", "luogoNascita", new Date(1997,3,9), 3, "numero", null);
        ArrayList<ProgFormativo> lista = new ArrayList<>();

        lista = ProgettoFormativoDAO.findAllByStudente(studente);
        assertEquals(lista.size(),3);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test_2findAllByDirettore(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ProgettoFormativoDAO.setConnectionPool(pool);
        Direttore direttore = new Direttore(null,null,null,"0000001",null,null);
        ArrayList<ProgFormativo> lista = new ArrayList<>();

        lista = ProgettoFormativoDAO.findAllByDirettore(direttore);
        assertEquals(lista.size(),0);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_3findAllByTutorAziendale(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ProgettoFormativoDAO.setConnectionPool(pool);
        TutorAz tutorAz= new TutorAz(null, null, null, null, null, "eteye", null, null, null);
        ArrayList<ProgFormativo> lista = new ArrayList<>();

        lista = ProgettoFormativoDAO.findAllByTutorAziendale(tutorAz);
        assertEquals(lista.size(),0);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test_4findAllByTutorAccademico(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ProgettoFormativoDAO.setConnectionPool(pool);
        TutorAc tutorAc= new TutorAc(null, null, null, "matricola", null, null);
        ArrayList<ProgFormativo> lista = new ArrayList<>();

        lista = ProgettoFormativoDAO.findAllByTutorAccademico(tutorAc);
        assertEquals(lista.size(),0);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Test_4findAllBySegreteria(){
        MySQLConnectionPoolFreeSqlDB pool = new MySQLConnectionPoolFreeSqlDB();
        ProgettoFormativoDAO.setConnectionPool(pool);
        Segreteria segreteria = new Segreteria(null,null,null);

        ArrayList<ProgFormativo> lista = new ArrayList<>();

        lista = ProgettoFormativoDAO.findAllBySegreteria(segreteria);
        assertEquals(lista.size(),0);
        try {
            pool.closeAllConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
