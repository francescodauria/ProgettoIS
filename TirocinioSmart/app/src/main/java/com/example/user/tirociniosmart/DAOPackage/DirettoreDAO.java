package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.user.tirociniosmart.EntityPackage.Direttore;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public class DirettoreDAO extends GenericDAO {

    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static Direttore findByMatricola(String matricola) throws SQLException {
        Direttore direttore = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Direttore WHERE Matricola="+matricola);

        ResultSet rs = null;
        rs = stt.executeQuery();

        while(rs.next()) {
            String matr = rs.getString("Matricola");
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            direttore = new Direttore(username,password,"direttore",matr,nome,cognome);
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return direttore;

    }


    public static void insert() {
    }

    public static void update() {
    }

    public static void search() {
    }

}
