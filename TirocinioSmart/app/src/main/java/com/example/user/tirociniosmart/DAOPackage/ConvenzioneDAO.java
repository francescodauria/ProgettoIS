package com.example.user.tirociniosmart.DAOPackage;

import com.example.user.tirociniosmart.EntityPackage.Convenzione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ConvenzioneDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }

    public static ArrayList<Convenzione> findByDirettore(String id) throws SQLException {

        ArrayList<Convenzione> listaConvenzioni = new ArrayList<>();

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Convenzione WHERE stato='IN CORSO' and id="+id);

        ResultSet rs = null;

        rs = stt.executeQuery();

        while (rs.next()) {
            String idConvenzione = rs.getString("ID");
            Date dataStipula = Date.valueOf(rs.getString("Data_Stipula"));
            String aziendaID = rs.getString("AziendaID");
            String direttoreMatricola = rs.getString("Direttore_DipartimentoMatricola");
            String stato = rs.getString("Stato");
            Convenzione convenzione = new Convenzione(AziendaDAO.findById(aziendaID), dataStipula, DirettoreDAO.findByMatricola(direttoreMatricola), stato);
            listaConvenzioni.add(convenzione);
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return listaConvenzioni;
    }

    public static Convenzione findConvenzioneTutor(String aziendaID) throws SQLException {

        Convenzione convenzione = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Convenzione WHERE aziendaID="+aziendaID);

        ResultSet rs = null;

        rs = stt.executeQuery();

        while (rs.next()) {
            String idConvenzione = rs.getString("ID");
            Date dataStipula = Date.valueOf(rs.getString("Data_Stipula"));
            String azID = rs.getString("AziendaID");
            String direttoreMatricola = rs.getString("Direttore_DipartimentoMatricola");
            String stato = rs.getString("Stato");
            convenzione = new Convenzione(AziendaDAO.findById(azID), dataStipula, DirettoreDAO.findByMatricola(direttoreMatricola), stato);
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return convenzione;
    }

    public static boolean cambiaStato(String stato, String idConvenzione) throws SQLException {
        Convenzione convenzione = null;
        Connection newConnection = null;
        PreparedStatement stt = null;
        boolean result = false;

        try {

            newConnection = (Connection) genericConnectionPool.getConnection();

            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");

            stt = newConnection.prepareStatement("UPDATE Convenzione SET Stato = ? WHERE ID=?");
            stt.setString(1,stato);
            stt.setString(2,idConvenzione);
            stt.executeUpdate();

            result = true;

        } catch (SQLException e) {

            result = false;

        } finally {
            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return result;
        }

    }

    public static String insert(Convenzione convenzione) throws SQLException {

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;
            if(ConvenzioneDAO.checkConvenzione(convenzione))
            {
                stt = newConnection.prepareStatement("INSERT INTO Convenzione (ID, AziendaID, Direttore_DipartimentoMatricola, Stato) " +
                        " VALUES (?,?,?,?)");
                stt.setString(1, String.valueOf(convenzione.getId()));
                stt.setString(2, convenzione.getAzienda().getId());
                stt.setString(3, convenzione.getDirettore().getMatricola());
                stt.setString(4, convenzione.getStato());
                stt.executeUpdate();

                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);

                return "Inserimento avvenuto correttamente";
            }
            else return "C'è già una convenzione in attesa di accettazione";
        } catch (SQLException e) {
            return "Connessione al database non presente";
        }


    }

    public static String update() {
        return "";
    }

    public static void search() {
    }

    public static boolean checkConvenzione(Convenzione convenzione) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();
        newConnection.setAutoCommit(false);
        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Convenzione WHERE AziendaID = ? AND Stato = ?");
        stt.setString(1,convenzione.getAzienda().getId());
        stt.setString(2, "IN CORSO");
        ResultSet rs=stt.executeQuery();
        if(rs.next()) {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return false;
        } else {
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return true;
        }
    }

}