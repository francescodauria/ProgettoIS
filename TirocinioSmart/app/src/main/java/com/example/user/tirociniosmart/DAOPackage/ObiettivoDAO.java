package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.Obiettivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public class ObiettivoDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static String insert(Obiettivo obiettivo) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(ObiettivoDAO.checkObiettivo(obiettivo)) {
                stt = newConnection.prepareStatement("INSERT INTO Obiettivo_formativo (Nome,Descrizione,AziendaID) VALUES (?,?,?)");
                stt.setString(1,obiettivo.getNome());
                stt.setString(2,obiettivo.getDescrizione());
                stt.setString(3,obiettivo.getAzienda().getId());
                stt.executeUpdate();

                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);

                return "Inserimento avvenuto correttamente";
            } else
                return "Esiste già un obiettivo con questo nome";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

    public static boolean checkObiettivo(Obiettivo obiettivo) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Obiettivo_formativo WHERE Nome = ?");
        stt.setString(1,obiettivo.getNome());
        ResultSet rs=stt.executeQuery();
        if(rs.next())
            return false;
        else
            return true;
    }

    public static String remove(Obiettivo obiettivo) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(!ObiettivoDAO.checkObiettivo(obiettivo)) {
                stt = newConnection.prepareStatement("DELETE FROM Obiettivo_formativo WHERE Nome = ?");
                stt.setString(1,obiettivo.getNome());
                stt.executeUpdate();

                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);

                return "Rimozione avvenuta correttamente";
            } else
                return "Non esiste un obiettivo con questo nome";
        } catch (SQLException e) {
            return "Connessione al database non presente";
        }
    }


    public static String update() {
        return "";
    }

    public static void search() {
    }

}
