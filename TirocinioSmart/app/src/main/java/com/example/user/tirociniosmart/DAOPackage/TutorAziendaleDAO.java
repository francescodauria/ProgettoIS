package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class TutorAziendaleDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static TutorAz findByCF(String codFiscale)  {
        TutorAz tutorAz = null;

        Connection newConnection = null;

        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("SELECT * FROM Tutor_Aziendale WHERE CF=?");
            stt.setString(1,codFiscale);


        ResultSet rs = null;
            rs = stt.executeQuery();

            while(rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                String cf = rs.getString("CF");
                String email = rs.getString("Email");
                String numeroTel = rs.getString("N_tel");
                String aziendaId = rs.getString("AziendaID");
                AziendaDAO.setConnectionPool(genericConnectionPool);
                Azienda azienda = AziendaDAO.findById(aziendaId);
                tutorAz = new TutorAz(username, password, "Tutor Aziendale", nome, cognome, cf, email, numeroTel, azienda);
            }

            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return tutorAz;
        } catch(SQLException e) {
            return null;
        }
    }

    public static String insert(TutorAz tutor, String idAzienda) {
        Connection newConnection = null;
        String s;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(TutorAziendaleDAO.checkTutor(tutor))
            {
                stt=newConnection.prepareStatement("INSERT INTO Tutor_Aziendale (CF,Nome,Cognome,Email,Password,Username,N_tel,AziendaID)" +
                        "VALUES (?,?,?,?,?,?,?,?)");
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
                s="Inserimento del tutor avvenuto correttamente";
                System.out.println(s);
                return s;
            }
            else s="Tutor già presente"; System.out.println(s);return s;

        }catch(SQLException e)
        {
            s="Connessione al database non presente";System.out.println(s);
            e.printStackTrace();return s;
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

    public static TutorAz findByUtente(Utente utente) throws SQLException {
        TutorAz tutorAz = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Tutor_Aziendale WHERE Username = ? and Password = ?");
        stt.setString(1, utente.getUsername());
        stt.setString(2, utente.getPassword());

        ResultSet rs = null;
        rs = stt.executeQuery();

        if (rs.next()) {
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String cf = rs.getString("CF");
            String email = rs.getString("Email");
            String numeroTel = rs.getString("N_tel");
            String aziendaId = rs.getString("AziendaID");
            AziendaDAO.setConnectionPool(genericConnectionPool);
            Azienda azienda = AziendaDAO.findById(aziendaId);
            tutorAz = new TutorAz(username, password, "Tutor Aziendale", nome, cognome, cf, email, numeroTel, azienda);
        } else {
            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return null;
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return tutorAz;
    }

    public static String cambioPassword(Utente utente, String newPassword)  {
        Connection newConnection = null;
        PreparedStatement stt = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");

            stt = newConnection.prepareStatement("UPDATE Tutor_Aziendale SET Password = ? WHERE Username = ? and Password = ?");
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

    public static TutorAz getTutorAzByAzienda(Azienda azienda)  {
        TutorAz tutorAz = null;

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("SELECT * FROM Tutor_Aziendale WHERE AziendaID = ?");
            stt.setString(1, azienda.getId());

            ResultSet rs = null;
            rs = stt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String nome = rs.getString("Nome");
                String cognome = rs.getString("Cognome");
                String cf = rs.getString("CF");
                String email = rs.getString("Email");
                String numeroTel = rs.getString("N_tel");
                String aziendaId = rs.getString("AziendaID");
                tutorAz = new TutorAz(username, password, "Tutor Aziendale", nome, cognome, cf, email, numeroTel, azienda);
            } else {
                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);

                return null;
            }

            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return tutorAz;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
