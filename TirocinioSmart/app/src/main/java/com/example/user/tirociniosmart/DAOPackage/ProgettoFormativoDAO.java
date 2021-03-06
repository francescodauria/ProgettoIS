package com.example.user.tirociniosmart.DAOPackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.user.tirociniosmart.EntityPackage.Azienda;
import com.example.user.tirociniosmart.EntityPackage.Direttore;
import com.example.user.tirociniosmart.EntityPackage.ProgFormativo;

import com.example.user.tirociniosmart.EntityPackage.Segreteria;
import com.example.user.tirociniosmart.EntityPackage.Studente;
import com.example.user.tirociniosmart.EntityPackage.TutorAc;
import com.example.user.tirociniosmart.EntityPackage.TutorAz;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.StudentActivity;
import com.example.user.tirociniosmart.UtenzaPackage.ActivityPackage.TutorAzActivity;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.sql.Types.NULL;

/**
 * Created by User on 17/01/2018.
 */

public class ProgettoFormativoDAO extends GenericDAO {

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
     * @param progetto
     * @return
     */
    public static String insert(ProgFormativo progetto) {
        if (progetto.getStudente().getNumeroTirocini() > 0) {
            Connection newConnection = null;
            try {
                newConnection = (Connection) genericConnectionPool.getConnection();
                newConnection.setAutoCommit(false);
                System.out.println("Database connesso");
                PreparedStatement stt = null;
                if (ProgettoFormativoDAO.checkProgetto(progetto)) {
                    stt = newConnection.prepareStatement("INSERT INTO Progetto_Formativo VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    Bitmap firmaStudente = progetto.getFirmaStudente();
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    firmaStudente.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
                    byte[] bitmapdata = bos.toByteArray();

                    ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
                    System.out.println(bitmapdata.length);

                    System.out.println(bs);
                    Blob b1= null;
                    Blob b3= null;
                    Blob b4= null;

                    stt.setInt(1,NULL);
                    stt.setBlob(2,b1);
                    stt.setBinaryStream(3, bs, bitmapdata.length);

                    stt.setBlob(4,b3);
                    stt.setBlob(5,b4);
                    stt.setString(6, progetto.getListaObiettivi());

                    stt.setString(7, progetto.getStato());
                    stt.setString(8,null);
                    stt.setInt(9, progetto.getNumeroOre());
                    stt.setDate(10, progetto.getDataInizio());
                    stt.setDate(11, progetto.getDataFine());
                    stt.setDate(12, null);
                    stt.setString(13, progetto.getStudente().getMatricola());
                    stt.setString(14, progetto.getTutorAc().getMatricola());
                    stt.setString(15, progetto.getTutorAz().getCF());
                    stt.setString(16, progetto.getDirettore().getMatricola());
                    stt.executeUpdate();
                    newConnection.commit();
                    stt.close();
                    genericConnectionPool.releaseConnection(newConnection);
                    return "Inserimento avvenuto con successo";
                } else
                    return "Inserimento non avvenuto poiché è già presente un progetto formativo";
            } catch (SQLException e) {
                e.printStackTrace();
                return "Connessione al database non presente";

            }
        }
        else return "Non ci sono tirocini disponibili";
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
     * @param studente
     * @return
     */
    public static ArrayList<ProgFormativo> findAllByStudente(Studente studente) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE StudenteMatricola = ? Order by Stato");
            stt.setString(1,studente.getMatricola());
            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next()) {

                int id=rs.getInt(1);

                Blob firmaDirettore=rs.getBlob(2);
                Bitmap firmaDir=null;
                if(firmaDirettore!=null) {
                    byte[] imgData1 = firmaDirettore.getBytes(1, (int) firmaDirettore.length());
                    firmaDir = BitmapFactory.decodeByteArray(imgData1, 0, imgData1.length);
                }


                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                if(firmaStudente!=null) {
                    byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                    firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);
                }
                Blob firmaTutorAziendale=rs.getBlob(4);
                Bitmap firmaTutor =null;
                if(firmaTutorAziendale!=null) {
                    byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
                    firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);
                }

                Blob firmaTutorAccademico=rs.getBlob(5);
                Bitmap firmaTutorAc = null;
                if(firmaTutorAccademico!=null) {
                    byte[] imgData4 = firmaTutorAccademico.getBytes(1, (int) firmaTutorAccademico.length());
                    firmaTutorAc = BitmapFactory.decodeByteArray(imgData4, 0, imgData4.length);
                }
                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
                TutorAziendaleDAO.setConnectionPool(StudentActivity.pool);


                TutorAc tutorAc = TutorAccademicoDAO.findByMatricola(rs.getString(14));
                TutorAz tutorAz = TutorAziendaleDAO.findByCF(rs.getString(15));
                Direttore direttore = new Direttore(null,null,null,rs.getString(16),null,null);



                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaTutorAcc(firmaTutorAc);
                progetto.setId(id);
                progetto.setGetFirmaTutorAz(firmaTutor);
                progetto.setFirmaDirettore(firmaDir);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            return null;
        }

    }

    /**
     *
     * @param tutorAz
     * @return
     */
    public static ArrayList<ProgFormativo> findAllByTutorAziendale(TutorAz tutorAz) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE Tutor_AziendaleCF = ? and Stato = ? and FirmaTutorAziendale IS NULL");
            stt.setString(1,tutorAz.getCF());
            stt.setString(2,"IN CORSO");
            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next()) {

                int id=rs.getInt(1);

                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);

                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(TutorAzActivity.pool);

                Studente studente = StudenteDAO.findByMatricola(rs.getString(13));
                TutorAc tutorAc = TutorAccademicoDAO.findByMatricola(rs.getString(14));
                Direttore direttore = new Direttore(null,null,null,rs.getString(16),null,null);

                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaStudente(firmaStud);
                progetto.setId(id);
                progetto.setGetFirmaTutorAz(null);
                progetto.setFirmaTutorAcc(null);
                progetto.setFirmaDirettore(null);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param tutorAc
     * @return
     */
    public static ArrayList<ProgFormativo> findAllByTutorAccademico(TutorAc tutorAc) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE Tutor_AccademicoMatricola = ? and Stato = ? and FirmaTutorAziendale IS NOT NULL and FirmaTutorAccademico IS NULL");
            //Possibile problema con IS NOT NULL, eventualmente provare come parametrica
            stt.setString(1,tutorAc.getMatricola());
            stt.setString(2,"IN CORSO");

            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next()) {

                int id=rs.getInt(1);

                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);
                Blob firmaTutorAziendale=rs.getBlob(4);
                Bitmap firmaTutor =null;
                if(firmaTutorAziendale!=null) {
                    byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
                    firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);
                }

                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
                TutorAziendaleDAO.setConnectionPool(StudentActivity.pool);

                Studente studente = StudenteDAO.findByMatricola(rs.getString(13));
                TutorAz tutorAz = TutorAziendaleDAO.findByCF(rs.getString(15));
                Direttore direttore = new Direttore(null,null,null,rs.getString(16),null,null);

                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaStudente(firmaStud);
                progetto.setGetFirmaTutorAz(firmaTutor);
                progetto.setFirmaTutorAcc(null);
                progetto.setFirmaDirettore(null);
                progetto.setId(id);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     * @param direttore
     * @return
     */
    public static ArrayList<ProgFormativo> findAllByDirettore(Direttore direttore) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE Direttore_DipartimentoMatricola = ? and Stato = ? and FirmaTutorAziendale  IS NOT NULL and FirmaTutorAccademico IS NOT NULL and FirmaDirettore IS NULL");
            //Possibile problema con IS NOT NULL, eventualmente provare come parametrica
            stt.setString(1,direttore.getMatricola());
            stt.setString(2,"IN CORSO");

            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next()) {

                int id=rs.getInt(1);

                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);
                Blob firmaTutorAziendale=rs.getBlob(4);
                Bitmap firmaTutor =null;
                if(firmaTutorAziendale!=null) {
                    byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
                    firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);
                }
                Blob firmaTutorAccademico=rs.getBlob(5);
                Bitmap firmaTutorAc = null;
                if(firmaTutorAccademico!=null) {
                    byte[] imgData4 = firmaTutorAccademico.getBytes(1, (int) firmaTutorAccademico.length());
                    firmaTutorAc = BitmapFactory.decodeByteArray(imgData4, 0, imgData4.length);
                }

                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
                TutorAziendaleDAO.setConnectionPool(StudentActivity.pool);

                Studente studente = StudenteDAO.findByMatricola(rs.getString(13));
                TutorAc tutorAc = TutorAccademicoDAO.findByMatricola(rs.getString(14));
                TutorAz tutorAz = TutorAziendaleDAO.findByCF(rs.getString(15));

                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaStudente(firmaStud);
                progetto.setGetFirmaTutorAz(firmaTutor);
                progetto.setFirmaTutorAcc(firmaTutorAc);
                progetto.setId(id);
                progetto.setFirmaDirettore(null);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 
     * @param segreteria
     * @return
     */
    public static ArrayList<ProgFormativo> findAllBySegreteria(Segreteria segreteria) {
        Log.d("fuori al while", "prova");

        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);

            TutorAccademicoDAO.setConnectionPool(genericConnectionPool);
            TutorAziendaleDAO.setConnectionPool(genericConnectionPool);

            System.out.println("Database connesso");
            PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE Stato = ? and FirmaDirettore IS NOT NULL");
            //Possibile problema con IS NOT NULL, eventualmente provare come parametrica
            stt.setString(1,"IN CORSO");

            ArrayList<ProgFormativo> progetti=new ArrayList<>();
            ResultSet rs=stt.executeQuery();
            while(rs.next()) {

                int id=rs.getInt(1);

                Blob firmaStudente = rs.getBlob(3);
                Bitmap firmaStud =null;
                byte[] imgData2 = firmaStudente.getBytes(1, (int) firmaStudente.length());
                firmaStud = BitmapFactory.decodeByteArray(imgData2, 0, imgData2.length);
                Blob firmaTutorAziendale=rs.getBlob(4);
                Bitmap firmaTutor =null;
                if(firmaTutorAziendale!=null) {
                    byte[] imgData3 = firmaTutorAziendale.getBytes(1, (int) firmaTutorAziendale.length());
                    firmaTutor = BitmapFactory.decodeByteArray(imgData3, 0, imgData3.length);
                }
                Blob firmaTutorAccademico=rs.getBlob(5);
                Bitmap firmaTutorAc = null;
                if(firmaTutorAccademico!=null) {
                    byte[] imgData4 = firmaTutorAccademico.getBytes(1, (int) firmaTutorAccademico.length());
                    firmaTutorAc = BitmapFactory.decodeByteArray(imgData4, 0, imgData4.length);
                }
                Blob firmaDirettore=rs.getBlob(2);
                Bitmap firmaDir=null;
                if(firmaDirettore!=null) {
                    byte[] imgData1 = firmaDirettore.getBytes(1, (int) firmaDirettore.length());
                    firmaDir = BitmapFactory.decodeByteArray(imgData1, 0, imgData1.length);
                }

                String obiettivi=rs.getString(6);
                String stato=rs.getString(7);
                String motivazione=rs.getString(8);
                int ore=rs.getInt(9);
                Date data_inizio=rs.getDate(10);
                Date data_fine=rs.getDate(11);
                Date data_stipula=rs.getDate(12);

                TutorAccademicoDAO.setConnectionPool(StudentActivity.pool);
                TutorAziendaleDAO.setConnectionPool(StudentActivity.pool);

                Studente studente = StudenteDAO.findByMatricola(rs.getString(13));
                Direttore direttore = DirettoreDAO.findByMatricola(rs.getString(16));
                TutorAz tutorAz = TutorAziendaleDAO.findByCF(rs.getString(15));
                TutorAc tutorAc = TutorAccademicoDAO.findByMatricola(rs.getString(14));

                ProgFormativo progetto=new ProgFormativo(stato,motivazione,ore,obiettivi,data_inizio,data_fine,data_stipula,firmaStud,studente,direttore,tutorAc,tutorAz);
                progetto.setFirmaStudente(firmaStud);
                progetto.setGetFirmaTutorAz(firmaTutor);
                progetto.setId(id);
                progetto.setFirmaTutorAcc(firmaTutorAc);
                progetto.setFirmaDirettore(null);
                progetti.add(progetto);

            }
            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return progetti;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     *
     */
    public static void search() {
    }

    /**
     *
     * @param progetto
     * @return
     * @throws SQLException
     */
    public static boolean checkProgetto(ProgFormativo progetto) throws SQLException {
        Connection newConnection = (Connection) genericConnectionPool.getConnection();

        newConnection.setAutoCommit(false);

        System.out.println("Database connesso");
        PreparedStatement stt = newConnection.prepareStatement("SELECT * FROM Progetto_Formativo WHERE StudenteMatricola = ? AND Stato = ?");
        stt.setString(1,progetto.getStudente().getMatricola());
        stt.setString(2,"IN CORSO");
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

    /**
     *
     * @param progFormativo
     * @return
     * @throws SQLException
     */
    public static String insertFirmaByTutorAziendale(ProgFormativo progFormativo)  {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("UPDATE Progetto_Formativo SET FirmaTutorAziendale = ? WHERE ID = ?");
            Bitmap firma = progFormativo.getGetFirmaTutorAz();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            firma.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            stt.setBinaryStream(1, bs,bitmapdata.length);
            stt.setInt(2, progFormativo.getId());
            stt.executeUpdate();

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Firma inserita da parte del tutor aziendale";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

    /**
     *
     * @param progFormativo
     * @return
     * @throws SQLException
     */
    public static String insertFirmaByTutorAccademico(ProgFormativo progFormativo) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;

            System.out.println(progFormativo.getId());
            stt = newConnection.prepareStatement("UPDATE Progetto_Formativo SET FirmaTutorAccademico = ? WHERE ID = ?");
            Bitmap firma = progFormativo.getFirmaTutorAcc();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            firma.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            stt.setBinaryStream(1, bs,bitmapdata.length);
            stt.setInt(2, progFormativo.getId());
            stt.executeUpdate();

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Accettazione progetto formativo avvenuta correttamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

    /**
     *
     * @param progFormativo
     * @return
     * @throws SQLException
     */
    public static String insertFirmaByDirettore(ProgFormativo progFormativo)  {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("UPDATE Progetto_Formativo SET FirmaDirettore = ? WHERE ID = ?");
            Bitmap firma = progFormativo.getFirmaDirettore();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            firma.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            stt.setBinaryStream(1, bs,bitmapdata.length);
            stt.setInt(2, progFormativo.getId());
            stt.executeUpdate();

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Accettazione progetto formativo avvenuta correttamente";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }

    }

    /**
     *
     * @param progFormativo
     * @return
     */
    public static String acceptProgettoFormativoBySegreteria(ProgFormativo progFormativo) {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;

            System.out.println(progFormativo.getId());
            System.out.println(progFormativo.getStato());

            stt = newConnection.prepareStatement("UPDATE Progetto_Formativo SET Data_Stipula = ?, Stato = ? WHERE ID = ?");
            stt.setDate(1, progFormativo.getDataStipula());
            stt.setString(2, progFormativo.getStato());
            stt.setInt(3, progFormativo.getId());
            stt.executeUpdate();

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Progetto formativo accettato dalla segreteria";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

    /**
     *
     * @param progFormativo
     * @return
     * @throws SQLException
     */
    public static String rifiutaProgettoFormativo(ProgFormativo progFormativo)  {
        Connection newConnection = null;
        try {
            newConnection = (Connection) genericConnectionPool.getConnection();
            newConnection.setAutoCommit(false);
            System.out.println("Database connesso");
            PreparedStatement stt = null;

            stt = newConnection.prepareStatement("UPDATE Progetto_Formativo SET Stato = ? WHERE ID = ?");
            stt.setString(1, progFormativo.getStato());
            stt.setInt(2, progFormativo.getId());
            stt.executeUpdate();

            newConnection.commit();
            stt.close();
            genericConnectionPool.releaseConnection(newConnection);
            return "Progetto formativo rifiutato";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Connessione al database non presente";
        }
    }

}
