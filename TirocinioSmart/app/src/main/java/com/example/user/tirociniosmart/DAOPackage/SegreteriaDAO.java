package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public class SegreteriaDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static Segreteria findByUtente(Utente utente) throws SQLException {
        Segreteria segreteria = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Segreteria WHERE Username ="+utente.getUsername());

        ResultSet rs = null;
        rs = stt.executeQuery();

        if (rs.next()) {
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            segreteria = new Segreteria(username,password,"Segreteria");
        } else {
            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return null;
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return segreteria;
    }


    public static String insert() {
        return "";
    }

    public static String update() {
        return "";
    }

    public static void search() {
    }

}
