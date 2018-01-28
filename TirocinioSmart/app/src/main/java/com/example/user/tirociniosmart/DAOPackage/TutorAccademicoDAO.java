package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class TutorAccademicoDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    /**
     *
     * @param connectionPool
     */
    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    /**
     *
     * @param matricola
     * @return
     */
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

    /**
     *
     * @param tutor
     * @return
     */
    public static String insert(TutorAc tutor) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(TutorAccademicoDAO.checkTutor(tutor)) {
                stt=newConnection.prepareStatement("INSERT INTO Tutor_Accademico (Matricola,Nome,Cognome,Username,Password)" +
                        "VALUES (?,?,?,?,?)");
                stt.setString(1,tutor.getMatricola());
                stt.setString(2,tutor.getNome());
                stt.setString(3,tutor.getCognome());
                stt.setString(4,tutor.getUsername());
                stt.setString(5,tutor.getPassword());
                stt.executeUpdate();
                newConnection.commit();
                stt.close();
                genericConnectionPool.releaseConnection(newConnection);
                return "Inserimento del tutor avvenuto correttamente";
            }
            else return "Tutor già presente";

        }catch(SQLException e)
        {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }



    /**
     *
     * @param tutor
     * @return
     * @throws SQLException
     */
    public static boolean checkTutor(TutorAc tutor) throws SQLException {
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

    /**
     *
     * @param utente
     * @return
     * @throws SQLException
     */
    public static TutorAc findByUtente(Utente utente) throws SQLException {
        TutorAc tutorAc = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Tutor_Accademico WHERE Username = ? and Password = ?");
        stt.setString(1, utente.getUsername());
        stt.setString(2, utente.getPassword());

        ResultSet rs = null;
        rs = stt.executeQuery();

        if (rs.next()) {
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String matricola = rs.getString("Matricola");
            tutorAc = new TutorAc(username, password, "Tutor Accademico", matricola, nome, cognome);
        } else {
            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return null;
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return tutorAc;
    }

    /**
     *
     * @param utente
     * @param newPassword
     * @return
     */
    public static String cambioPassword(Utente utente, String newPassword) {
        Connection newConnection = null;
        PreparedStatement stt = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");

            stt = newConnection.prepareStatement("UPDATE Tutor_Accademico SET Password = ? WHERE Username = ? and Password = ?");
            stt.setString(1, newPassword);
            stt.setString(2, utente.getUsername());
            stt.setString(3, utente.getPassword());
            stt.executeUpdate();
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Cambio password avvenuto correttamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

    /**
     *
     * @return
     */
    public static ArrayList<TutorAc> getAllTutorAc()  {
        try {
            ArrayList<TutorAc> lista = new ArrayList<TutorAc>();
            Connection newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            stt = newConnection.prepareStatement("SELECT * FROM Tutor_Accademico order by nome");
            ResultSet rs = null;
            rs = stt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                String matricola = rs.getString("Matricola");
                TutorAc tutorAc = new TutorAc(username, password, "Tutor Accademico", matricola, nome, cognome);
                lista.add(tutorAc);
            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return lista;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
