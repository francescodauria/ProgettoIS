package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ProgFormativo {

    private String id;
    private String stato;
    private String motivazione;
    private int numeroOre;
    private Bitmap firmaTutorAcc;
    private Bitmap getFirmaTutorAz;
    private ArrayList<Obiettivo> listaObiettivi;
    private Date dataInizio;
    private Date dataFine;
    private Date dataStipula;


    public ProgFormativo(String id, String stato, String motivazione, int numeroOre, ArrayList<Obiettivo> listaObiettivi, Date dataInizio, Date dataFine) {
        this.id = id;
        this.stato = stato;
        this.motivazione = motivazione;
        this.numeroOre = numeroOre;
        this.listaObiettivi = listaObiettivi;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public String getId() {
        return id;
    }


    public void addObiettivoFormativo(Obiettivo o){
        listaObiettivi.add(o);
    }

    public void removeObiettivoFormativo(Obiettivo o){
        if( listaObiettivi.contains(o))
            listaObiettivi.remove(o);

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public int getNumeroOre() {
        return numeroOre;
    }

    public void setNumeroOre(int numeroOre) {
        this.numeroOre = numeroOre;
    }

    public Bitmap getFirmaTutorAcc() {
        return firmaTutorAcc;
    }

    public void setFirmaTutorAcc(Bitmap firmaTutorAcc) {
        this.firmaTutorAcc = firmaTutorAcc;
    }

    public Bitmap getGetFirmaTutorAz() {
        return getFirmaTutorAz;
    }

    public void setGetFirmaTutorAz(Bitmap getFirmaTutorAz) {
        this.getFirmaTutorAz = getFirmaTutorAz;
    }

    public ArrayList<Obiettivo> getListaObiettivi() {
        return listaObiettivi;
    }

    public void setListaObiettivi(ArrayList<Obiettivo> listaObiettivi) {
        this.listaObiettivi = listaObiettivi;
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    public Date getDataStipula() {
        return dataStipula;
    }

    public void setDataStipula(Date dataStipula) {
        this.dataStipula = dataStipula;
    }
}
