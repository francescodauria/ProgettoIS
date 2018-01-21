package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Studente;


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


    public static boolean insert(ProgFormativo progetto) throws SQLException{
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;
        if(ProgettoFormativoDAO.checkProgetto(progetto))
        {
            stt=newConnection.prepareStatement("INSERT INTO Progetto_Formativo ('ID','FirmaStudente','Obiettivi','Stato','#Ore'" +
                    ",'Data_inizio','Data_fine','StudenteMatricola','Tutor_AccademicoMatricola','Tutor_AziendaleCF','Direttore_DipartimentoMatricola'" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?");
            stt.setString(1,progetto.getId());
            Bitmap firmaStudente=progetto.getFirmaStudente();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            firmaStudente.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            stt.setBlob(2,bs);
            stt.setString(3,progetto.getListaObiettivi());
            stt.setString(4,progetto.getStato());
            stt.setInt(5,progetto.getNumeroOre());
            stt.setDate(6,progetto.getDataInizio());
            stt.setDate(7,progetto.getDataFine());
            stt.setString(8,progetto.getMatrcolaStud());
            stt.setString(9,progetto.getMatricolaTutor());
            stt.setString(10,progetto.getCFTutor());
            stt.setString(11,progetto.getMatricolaDir());
            stt.executeUpdate();
            return true;
        }
        else return false;
    }

    public static void update() {
    }
    public static ArrayList<ProgFormativo> findAllByStudente(Studente studente) throws SQLException{
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Matricola = ?");
        stt.setString(1,studente.getMatricola());
        ArrayList<ProgFormativo> progetti=new ArrayList<>();
        ResultSet rs=stt.executeQuery();
        while(rs.next())
        {
            String id=rs.getString(1);
            Blob firmaDirettore=rs.getBlob(2);
            byte[] imgData1 = firmaDirettore.getBytes(1, (int) firmaDirettore.length());
            Bitmap firmaDir = BitmapFactory.decodeByteArray(imgData1, 0, imgData1.length);

            Blob firmaStudente = rs.getBlob(3);
            byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
            Bitmap firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);

            Blob firmaTutorAziendale=rs.getBlob(4);
            byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
            Bitmap firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);

            Blob firmaTutorAccademico=rs.getBlob(5);
            byte[] imgData4 = firmaTutorAccademico.getBytes(1, (int) firmaTutorAccademico.length());
            Bitmap firmaTutorAc = BitmapFactory.decodeByteArray(imgData4, 0, imgData4.length);

            String obiettivi=rs.getString(6);
            String stato=rs.getString(7);
            String motivazione=rs.getString(8);
            int ore=rs.getInt(9);
            Date data_inizio=rs.getDate(10);
            Date data_fine=rs.getDate(11);
            Date data_stipula=rs.getDate(12);
            String matricola_stud=rs.getString(13);
            String tutor_acc_matricola=rs.getString(14);
            String tutor_az_CF=rs.getString(15);
            String direttore=rs.getString(16);

            ProgFormativo progetto=new ProgFormativo(id,stato,motivazione,ore,obiettivi,data_inizio,data_fine,firmaStud,matricola_stud,tutor_acc_matricola,tutor_az_CF);
            progetto.setFirmaTutorAcc(firmaTutorAc);
            progetto.setGetFirmaTutorAz(firmaTutor);
            progetto.setFirmaDirettore(firmaDir);
            progetti.add(progetto);

        }
        return progetti;
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
        if(rs.next()) return false;
        else return true;
    }
}
