package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class AziendaDAO extends GenericDAO {
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
     * @param azienda
     * @return
     * @throws SQLException
     */
    public static String insert(Azienda azienda) throws SQLException {
        String s;
        try {
            if (AziendaDAO.checkAzienda(azienda)&&AziendaDAO.checkAziendaEmail(azienda)) {
                Connection newConnection = (Connection) genericConnectionPool.getConnection();

                newConnection.setAutoCommit(false);

                System.out.println("Database connesso");
                PreparedStatement stt = newConnection.prepareStatement("INSERT INTO Azienda (ID, Nome, Sede, Descrizione, Logo, N_tel, Email)" +
                        "VALUES (?,?,?,?,?,?,?)");
                stt.setString(1, azienda.getId());
                stt.setString(2, azienda.getNome());
                stt.setString(3, azienda.getSede());
                stt.setString(4, azienda.getDescrizione());
                Bitmap logo = azienda.getLogo();

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                logo.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                byte[] bitmapdata = bos.toByteArray();
                ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                stt.setBinaryStream(5, bs,bitmapdata.length);
                stt.setString(6, azienda.getNumeroTel());
                stt.setString(7, azienda.getEmail());
                stt.executeUpdate();
                newConnection.commit();
                stt.close();

                genericConnectionPool.releaseConnection(newConnection);
                s="Inserimento dell'azienda avvenuto con successo";
                System.out.println(s);
                return s;
            } else s="L'azienda è già presente";

                System.out.println(s);
                return s;
        }catch (SQLException e)
        {
            s="Connessione al database non presente";
            e.printStackTrace();
            System.out.println(s);
            return s;
        }
    }

    /**
     *
     * @param azienda
     * @return
     * @throws SQLException
     */

    public static boolean checkAziendaEmail(Azienda azienda) throws SQLException
    {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Azienda WHERE Email = ?");
        stt.setString(1,azienda.getEmail());
        ResultSet rs=stt.executeQuery();
        if(rs.next()) {
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

    public static boolean checkAzienda(Azienda azienda) throws SQLException
    {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Azienda WHERE ID = ?");
        stt.setString(1,azienda.getId());
        ResultSet rs=stt.executeQuery();
        if(rs.next()) {
            System.out.println("Entrato nel check dell'azienda, è già presente");
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

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public static Azienda findById(String id) throws SQLException {
        Azienda azienda = null;

        try {
            Connection newConnection = (Connection) genericConnectionPool.getConnection();

            newConnection.setAutoCommit(false);

            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("SELECT * FROM Azienda WHERE id=?");
            stt.setString(1, id);


            ResultSet rs = null;
            rs = stt.executeQuery();

            while (rs.next()) {
                Blob dat = rs.getBlob("logo");

                byte[] imgData = dat.getBytes(1, (int) dat.length());

                String idAzienda = rs.getString("ID");
                String sede = rs.getString("Sede");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                String descrizione = rs.getString("Descrizione");
                String numero = rs.getString("N_tel");
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                azienda = new Azienda(idAzienda, nome, email, sede, descrizione, bitmap, numero, null);
            }

            newConnection.commit();
            stt.close();

            genericConnectionPool.releaseConnection(newConnection);

            return azienda;
        }
        catch (SQLException e){
            return null;
        }

    }

    /**
     *
     * @return
     */
    public static ArrayList<Azienda> getAllAziende()  {
        try {
            ArrayList<Azienda> lista = new ArrayList<Azienda>();
            Connection newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            stt = newConnection.prepareStatement("SELECT * FROM Azienda order by nome");
            ResultSet rs = null;
            rs = stt.executeQuery();
            while (rs.next()) {
                Blob dat = rs.getBlob("logo");
                byte[] imgData = dat.getBytes(1, (int) dat.length());
                String id = rs.getString("ID");
                String sede = rs.getString("Sede");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                String descrizione = rs.getString("Descrizione");
                String numero = rs.getString("N_tel");
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                Azienda a = new Azienda(id, nome, email, sede, descrizione, bitmap, numero, null);
                lista.add(a);
            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return lista;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static ArrayList<Azienda> getAllAziendeConvenzionate()  {
        try {
            ArrayList<Azienda> lista = new ArrayList<Azienda>();
            Connection newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;
            stt = newConnection.prepareStatement("SELECT * FROM Azienda INNER JOIN Convenzione ON Convenzione.AziendaID=Azienda.ID WHERE Convenzione.Stato='ACCETTATO'");
            ResultSet rs = null;
            rs = stt.executeQuery();
            while (rs.next()) {
                Blob dat = rs.getBlob("logo");
                byte[] imgData = dat.getBytes(1, (int) dat.length());
                String id = rs.getString("ID");
                String sede = rs.getString("Sede");
                String nome = rs.getString("Nome");
                String email = rs.getString("Email");
                String descrizione = rs.getString("Descrizione");
                String numero = rs.getString("N_tel");
                Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
                Azienda a = new Azienda(id, nome, email, sede, descrizione, bitmap, numero, null);
                lista.add(a);
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
