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

        stt = newConnection.prepareStatement("SELECT * FROM Segreteria WHERE Username = ? and Password = ?");
        stt.setString(1, utente.getUsername());
        stt.setString(2, utente.getPassword());

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

    public String cambioPassword(Utente utente, String newPassword) throws SQLException {
        Connection newConnection = null;
        PreparedStatement stt = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");

            stt = newConnection.prepareStatement("UPDATE Segreteria SET Password = ? WHERE Username = ? and Password = ?");
            stt.setString(1, newPassword);
            stt.setString(2, utente.getUsername());
            stt.setString(3, utente.getPassword());

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Cambio password avvenuto correttamente";
        } catch (SQLException e) {
            stt.close();
            e.printStackTrace();
            genericConnectionPool.releaseConnection(newConnection);
            return "Connessione al database non presente";
        }
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
