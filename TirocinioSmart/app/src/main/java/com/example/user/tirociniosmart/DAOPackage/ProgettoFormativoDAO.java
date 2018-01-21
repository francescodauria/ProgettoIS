package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.mysql.jdbc.Blob;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

            return true;
        }
        else return false;
    }

    public static void update() {
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
