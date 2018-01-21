package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ProgFormativo {

    private String stato;
    private String motivazione;
    private int numeroOre;
    private Bitmap firmaTutorAcc;
    private Bitmap getFirmaTutorAz;
    private Bitmap firmaStudente;
    private String listaObiettivi;
    private Date dataInizio;
    private Date dataFine;
    private Date dataStipula;
    private Bitmap firmaDirettore;
    private String matrcolaStud;
    private String matricolaDir;
    private String matricolaTutor;
    private String CFTutor;

    public ProgFormativo(String stato, String motivazione, int numeroOre, String listaObiettivi, Date dataInizio, Date dataFine,Date dataStipula, Bitmap firmaStudente, String matricolaStud, String matricolaDir,String matricolaTutor, String CFTutor) {

        this.stato = stato;
        this.motivazione = motivazione;
        this.numeroOre = numeroOre;
        this.listaObiettivi = listaObiettivi;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.firmaStudente=firmaStudente;
        this.matrcolaStud=matricolaStud;
        this.matricolaTutor=matricolaTutor;
        this.CFTutor=CFTutor;
        this.dataStipula = dataStipula;
        this.matricolaDir=matricolaDir;
    }



    public String getListaObiettivi() {
        return listaObiettivi;
    }

    public String getMatricolaStud() {
        return matrcolaStud;
    }

    public String getMatricolaDir() {
        return matricolaDir;
    }

    public String getMatricolaTutor() {
        return matricolaTutor;
    }

    public String getCFTutor() {
        return CFTutor;
    }

    public void setListaObiettivi(String listaObiettivi) {
        this.listaObiettivi = listaObiettivi;
    }

    public Bitmap getFirmaDirettore() {
        return firmaDirettore;
    }

    public void setFirmaDirettore(Bitmap firmaDirettore) {
        this.firmaDirettore = firmaDirettore;
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

    public Bitmap getFirmaStudente() {
        return firmaStudente;
    }
}
