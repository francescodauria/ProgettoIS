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

        stt = newConnection.prepareStatement("SELECT * FROM Convenzione WHERE stato='in corso' and id="+id);

        ResultSet rs = null;

        rs = stt.executeQuery();

        while (rs.next()) {
            String idConvenzione = rs.getString("ID");
            Date dataStipula = Date.valueOf(rs.getString("Data_Stipula"));
            String aziendaID = rs.getString("AziendaID");
            String direttoreMatricola = rs.getString("Direttore_DipartimentoMatricola");
            String stato = rs.getString("Stato");
            Convenzione convenzione = new Convenzione(idConvenzione, AziendaDAO.findById(aziendaID), dataStipula, DirettoreDAO.findByMatricola(direttoreMatricola), stato);
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
            convenzione = new Convenzione(idConvenzione, AziendaDAO.findById(azID), dataStipula, DirettoreDAO.findByMatricola(direttoreMatricola), stato);
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

            stt = newConnection.prepareStatement("UPDATE Convenzione SET Stato = "+stato+" WHERE ID="+idConvenzione);
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

    public static void insert(Convenzione convenzione) throws SQLException {

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("INSERT INTO Convenzione (`ID`, `Data_Stipula`, `AziendaID`, `Direttore_DipartimentoMatricola`, `Stato`) " +
                "                                   VALUES ("+ convenzione.getId() +", "+ convenzione.getDataStipula() +", "+ convenzione.getAzienda().getId() +", "
                                                             + convenzione.getDirettore().getMatricola() +", "+ convenzione.getStato() +")");
        stt.executeUpdate();

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return;
    }

    public static void update() {
    }

    public static void search() {
    }

}
