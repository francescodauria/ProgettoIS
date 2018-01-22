package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.TutorAc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public class TutorAccademicoDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static TutorAc findByMatricola(String matricola)  {
        TutorAc tutorAc = null;

        Connection newConnection = null;

        try {
            System.out.println(genericConnectionPool.toString()+ "tutor accademico");

            newConnection = (Connection) genericConnectionPool.getConnection();
            System.out.println(newConnection);
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("SELECT * FROM Tutor_Accademico WHERE Matricola=" + matricola);

            ResultSet rs = null;
            rs = stt.executeQuery();

            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                tutorAc = new TutorAc(username, password, "Tutor Accademico", matricola, nome, cognome);

            }

            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return tutorAc;
                   } catch(SQLException e) {
                        e.printStackTrace();
                         return null;

        }
        }



    public static String insert(TutorAc tutor) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(TutorAccademicoDAO.checkTutor(tutor)) {
                stt=newConnection.prepareStatement("INSERT INTO Tutor_Accademico (Matricola,Nome,Cognome,Password,Username" +
                        "VALUES (?,?,?,?,?");
                stt.setString(1,tutor.getMatricola());
                stt.setString(2,tutor.getNome());
                stt.setString(3,tutor.getCognome());
                stt.setString(4,tutor.getPassword());
                stt.setString(5,tutor.getUsername());
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
    public static boolean checkTutor(TutorAc tutor) throws SQLException
    {
        Connection newConnection = null;
        newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Tutor_Accademico WHERE Matricola = ?");
        stt.setString(1,tutor.getMatricola());
        ResultSet rs=stt.executeQuery();
        if(rs.next())
        {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return false;
        }
        else
        {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return true;
        }
    }
}
