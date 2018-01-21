package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.TutorAz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public class TutorAziendaleDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }


    public static String insert(TutorAz tutor, String idAzienda) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(TutorAziendaleDAO.checkTutor(tutor))
            {
                stt=newConnection.prepareStatement("INSERT INTO Tutor_Aziendale ('CF','Nome','Cognome','Email','Password','Username','N_tel','AziendaID'" +
                        "VALUES (?,?,?,?,?,?,?,?");
                stt.setString(1,tutor.getCF());
                stt.setString(2,tutor.getNome());
                stt.setString(3,tutor.getCognome());
                stt.setString(4,tutor.getEmail());
                stt.setString(5,tutor.getPassword());
                stt.setString(6,tutor.getUsername());
                stt.setString(7,tutor.getNumeroTel());
                stt.setString(8,idAzienda);
                stt.executeUpdate();
                newConnection.commit();
                stt.close();
                genericConnectionPool.releaseConnection(newConnection);
                return "Inserimento del tutor avvenuto correttamente";
            }
            else return "Tutor già presente";

        }catch(SQLException e)
        {
            return "Connessione al database non presente";
        }
    }

    public static String update() {
        return "";
    }

    public static void search() {
    }

    public static boolean checkTutor(TutorAz tutor) throws SQLException
    {
        Connection newConnection = null;
        newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Tutor_Aziendale WHERE CF = ?");
        stt.setString(1,tutor.getCF());
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
