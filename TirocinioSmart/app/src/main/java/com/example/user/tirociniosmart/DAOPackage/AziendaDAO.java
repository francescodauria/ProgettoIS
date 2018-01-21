package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Obiettivo;

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

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }


    public static boolean insert() {
        return true;
    }

    public static void update() {
    }

    public static void search() {
    }

    public static Azienda findById(String id) throws SQLException {
        Azienda azienda = null;

        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = null;

        stt = newConnection.prepareStatement("SELECT * FROM Azienda WHERE id="+id);

        ResultSet rs = null;
        rs = stt.executeQuery();

        while(rs.next()) {
            Blob dat = rs.getBlob("logo");

            byte[] imgData = dat.getBytes(1, (int) dat.length());

            String idAzienda = rs.getString("ID");
            String sede = rs.getString("Sede");
            String nome = rs.getString("Nome");
            String email = rs.getString("Email");
            String descrizione = rs.getString("Descrizione");
            String numero = rs.getString("N_tel");
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            azienda = new Azienda(idAzienda, nome, email,sede, descrizione, bitmap,numero, null);
        }

        newConnection.commit();
        stt.close();

        genericConnectionPool.releaseConnection(newConnection);

        return azienda;

    }

    public static ArrayList<Azienda>getAllAziende() throws SQLException {

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
            Azienda a = new Azienda(id, nome, email,sede, descrizione, bitmap,numero, null);
            lista.add(a);



        }


        newConnection.commit();
        stt.close();
        genericConnectionPool.releaseConnection(newConnection);

        return lista;
    }





}
