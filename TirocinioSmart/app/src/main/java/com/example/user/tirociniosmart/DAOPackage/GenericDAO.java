package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public abstract class GenericDAO {

    public static String cambioPassword(Utente utente, String newPassword) throws SQLException {
        Connection newConnection = null;
        PreparedStatement stt = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");

            if (utente instanceof Studente) {
                stt = newConnection.prepareStatement("UPDATE Studente SET Password = ? WHERE Username = ? and Password = ?");
                stt.setString(1, newPassword);
                stt.setString(2, utente.getUsername());
                stt.setString(3, utente.getPassword());
            } else if (utente instanceof TutorAz){
                stt = newConnection.prepareStatement("UPDATE Tutor_Aziendale SET Password = ? WHERE Username = ? and Password = ?");
                stt.setString(1, newPassword);
                stt.setString(2, utente.getUsername());
                stt.setString(3, utente.getPassword());
            } else if (utente instanceof TutorAc){
                stt = newConnection.prepareStatement("UPDATE Tutor_Accademico SET Password = ? WHERE Username = ? and Password = ?");
                stt.setString(1, newPassword);
                stt.setString(2, utente.getUsername());
                stt.setString(3, utente.getPassword());
            } else if (utente instanceof Direttore){
                stt = newConnection.prepareStatement("UPDATE Direttore_Dipartimento SET Password = ? WHERE Username = ? and Password = ?");
                stt.setString(1, newPassword);
                stt.setString(2, utente.getUsername());
                stt.setString(3, utente.getPassword());
            } else if (utente instanceof Segreteria){
                stt = newConnection.prepareStatement("UPDATE Segreteria SET Password = ? WHERE Username = ? and Password = ?");
                stt.setString(1, newPassword);
                stt.setString(2, utente.getUsername());
                stt.setString(3, utente.getPassword());
            } else {
                newConnection.commit();
                stt.close();
                genericConnectionPool.releaseConnection(newConnection);
                return "Caso impossibile";
            }

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

    private static GenericConnectionPool genericConnectionPool;


    public static String insert()throws SQLException {return "";} ;

    public static String update(){return "";};

    public static void search(){};

}
