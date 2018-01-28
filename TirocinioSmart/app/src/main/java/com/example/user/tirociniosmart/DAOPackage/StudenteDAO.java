package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.Utente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class StudenteDAO extends GenericDAO {


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
     * @throws SQLException
     */
    public static Studente findByMatricola(String matricola) throws SQLException {
        Studente studente = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Matricola ="+matricola);

        ResultSet rs = null;
        rs = stt.executeQuery();

        while(rs.next()) {
            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String indirizzo = rs.getString("Indirizzo");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String cf = rs.getString("CF");
            int n_tirocini = rs.getInt("#tirocini");
            String luogo_nascita = rs.getString("Luogo_Nascita");
            Date data_nascita = rs.getDate("Data_Nascita");
            String numero = rs.getString("N_tel");
            String email = rs.getString("Email");
            studente = new Studente(username, password, "Studente",
                    matricola, nome, cognome, cf,
                    email, indirizzo, luogo_nascita, data_nascita,
                    n_tirocini, numero, new ArrayList<ProgFormativo>());
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return studente;

    }

    /**
     *
     * @param studente
     * @return
     */
    public static String insert(Studente studente) {

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(StudenteDAO.checkStudente(studente)&&StudenteDAO.checkStudenteUsername(studente) && StudenteDAO.checkStudenteEmail(studente)) {
                stt = newConnection.prepareStatement("INSERT INTO Studente VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
/*                stt = newConnection.prepareStatement("INSERT INTO Studente (Matricola, Nome, Cognome," +
                        "Indirizzo, Username, Password, CF, #tirocini, Luogo_Nascita, Data_Nascita, N_Tel, Email)" +
                        " VALUES ('"+studente.getMatricola()+"','"+studente.getNome()+"','"+studente.getCognome()+"','"+studente.getIndirizzo()+"','"+studente.getUsername()+"','"+studente.getPassword()+"','"+studente.getCF()+"','"+studente.getNumeroTirocini()+"','"+studente.getLuogoNascita()+"','"+studente.getDataNascita()+"','"+studente.getNumeroTel()+"','"+studente.getEmail()+"')");*/

                stt.setString(1,studente.getMatricola());
                stt.setString(2,studente.getNome());
                stt.setString(3,studente.getCognome());
                stt.setString(4,studente.getIndirizzo());
                stt.setString(5,studente.getUsername());
                stt.setString(6,studente.getPassword());
                stt.setString(7,studente.getCF());
                stt.setInt(8, studente.getNumeroTirocini());
                stt.setString(9,studente.getLuogoNascita());
                stt.setDate(10,studente.getDataNascita());
                stt.setString(11,studente.getNumeroTel());
                stt.setString(12,studente.getEmail());
                stt.executeUpdate();

                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);

                return "Inserimento avvenuto correttamente";
            } else
                return "Esiste già uno studente con tale matricola, username o email";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }

    }

    /**
     *
     * @param studente
     * @return
     * @throws SQLException
     */
    public static boolean checkStudente(Studente studente) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Matricola = ?");
        stt.setString(1,studente.getMatricola());
        ResultSet rs=stt.executeQuery();
        if(rs.next()) return false;
        else return true;
    }
    public static boolean checkStudenteEmail(Studente studente) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Email = ?");
        stt.setString(1,studente.getEmail());
        ResultSet rs=stt.executeQuery();
        if(rs.next()) return false;
        else return true;
    }
    public static boolean checkStudenteUsername(Studente studente) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Username = ?");
        stt.setString(1,studente.getUsername());
        ResultSet rs=stt.executeQuery();
        if(rs.next()) return false;
        else return true;
    }
    /**
     *
     * @param utente
     * @return
     * @throws SQLException
     */
    public static Studente findByUtente(Utente utente) throws SQLException {
        Studente studente = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Studente WHERE Username = ? and Password = ?");
        stt.setString(1, utente.getUsername());
        stt.setString(2, utente.getPassword());


        ResultSet rs = null;
        rs = stt.executeQuery();

        if (rs.next()) {

            String nome = rs.getString("Nome");
            String cognome = rs.getString("Cognome");
            String indirizzo = rs.getString("Indirizzo");
            String username = rs.getString("Username");
            String password = rs.getString("Password");
            String matricola = rs.getString("Matricola");
            String cf = rs.getString("CF");
            int n_tirocini = rs.getInt("#tirocini");
            String luogo_nascita = rs.getString("Luogo_Nascita");
            Date data_nascita = rs.getDate("Data_Nascita");
            String numero = rs.getString("N_tel");
            String email = rs.getString("Email");
            studente = new Studente(username, password, "Studente",
                    matricola, nome, cognome, cf,
                    email, indirizzo, luogo_nascita, data_nascita,
                    n_tirocini, numero, new ArrayList<ProgFormativo>());
        } else {
            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return null;
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return studente;
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

            stt = newConnection.prepareStatement("UPDATE Studente SET Password = ? WHERE Username = ? and Password = ?");
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
    public static String update() {
        return "";
    }

    /**
     *
     */
    public static void search() {
    }

}
