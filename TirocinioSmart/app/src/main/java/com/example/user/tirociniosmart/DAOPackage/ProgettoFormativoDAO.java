package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;

import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ProgettoFormativoDAO extends GenericDAO {

    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }


    public static String insert(ProgFormativo progetto) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(ProgettoFormativoDAO.checkProgetto(progetto)) {
                stt = newConnection.prepareStatement("INSERT INTO Progetto_Formativo ('FirmaStudente','Obiettivi','Stato','#Ore'" +
                        ",'Data_inizio','Data_fine','StudenteMatricola','Tutor_AccademicoMatricola','Tutor_AziendaleCF','Direttore_DipartimentoMatricola'" +
                        "VALUES (?,?,?,?,?,?,?,?,?,?");

                Bitmap firmaStudente = progetto.getFirmaStudente();
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                firmaStudente.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();
                ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                stt.setBlob(1, bs);
                stt.setString(2, progetto.getListaObiettivi());
                stt.setString(3, progetto.getStato());
                stt.setInt(4, progetto.getNumeroOre());
                stt.setDate(5, progetto.getDataInizio());
                stt.setDate(6, progetto.getDataFine());
                stt.setString(7, progetto.getStudente().getMatricola());
                stt.setString(8, progetto.getTutorAc().getMatricola());
                stt.setString(9, progetto.getTutorAz().getCF());
                stt.setString(10, progetto.getDirettore().getMatricola());
                stt.executeUpdate();
                newConnection.commit();
                stt.close();
                genericConnectionPool.releaseConnection(newConnection);
                return "Inserimento avvenuto con successo";
            }
            else return "Inserimento non avvenuto poiché è già presente un progetto formativo";
        } catch (SQLException e) {
            return "Connessione al database non presente";
        }
    }

    public static String update() {
        return "";
    }
    public static ArrayList<ProgFormativo> findAllByStudente(Studente studente) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE StudenteMatricola = ? Order by Stato");
            stt.setString(1,studente.getMatricola());
            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next())
            {

                String id=rs.getString(1);

                Blob firmaDirettore=rs.getBlob(2);
                Bitmap firmaDir=null;
                if(firmaDirettore!=null) {
                    byte[] imgData1 = firmaDirettore.getBytes(1, (int) firmaDirettore.length());
                    firmaDir = BitmapFactory.decodeByteArray(imgData1, 0, imgData1.length);
                }


                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                if(firmaStudente!=null) {
                    byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                    firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);
                }
                Blob firmaTutorAziendale=rs.getBlob(4);
                Bitmap firmaTutor =null;
                if(firmaTutorAziendale!=null) {
                    byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
                    firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);
                }

                Blob firmaTutorAccademico=rs.getBlob(5);
                Bitmap firmaTutorAc = null;
                if(firmaTutorAccademico!=null) {
                    byte[] imgData4 = firmaTutorAccademico.getBytes(1, (int) firmaTutorAccademico.length());
                    firmaTutorAc = BitmapFactory.decodeByteArray(imgData4, 0, imgData4.length);
                }
                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
                TutorAziendaleDAO.setConnectionPool(StudentActivity.pool);


                TutorAc tutorAc = TutorAccademicoDAO.findByMatricola(rs.getString(14));
                TutorAz tutorAz = TutorAziendaleDAO.findByCF(rs.getString(15));

                Azienda a = tutorAz.getAzienda();


                System.out.println(a.getEmail());
                System.out.println(a.getNome());
                System.out.println(a.getSede());
                System.out.println(a.getDescrizione());
                System.out.println(a.getId());
                System.out.println();


                Direttore direttore = new Direttore(null,null,null,rs.getString(16),null,null);



                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaTutorAcc(firmaTutorAc);
                progetto.setGetFirmaTutorAz(firmaTutor);
                progetto.setFirmaDirettore(firmaDir);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            return null;
        }

    }
    public static void search() {
    }
    public static boolean checkProgetto(ProgFormativo progetto) throws SQLException
    {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE StudenteMatricola = ? AND Stato = 'IN CORSO");
        ResultSet rs=stt.executeQuery();
        if(rs.next())
        {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return false;
        }
        else {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return true;
        }
    }
}
