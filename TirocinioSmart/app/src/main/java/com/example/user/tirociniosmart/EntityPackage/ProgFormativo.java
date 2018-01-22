package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ProgFormativo {

    private int id;
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
    private Studente studente;
    private TutorAc tutorAc;
    private TutorAz tutorAz;
    private Direttore direttore;


    public ProgFormativo(String stato, String motivazione, int numeroOre, String listaObiettivi, Date dataInizio, Date dataFine,Date dataStipula, Bitmap firmaStudente, Studente studente, Direttore dir,TutorAc tutorAc, TutorAz tutorAz) {

        this.stato = stato;
        this.motivazione = motivazione;
        this.numeroOre = numeroOre;
        this.listaObiettivi = listaObiettivi;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.firmaStudente=firmaStudente;
        this.studente=studente;
        this.tutorAc = tutorAc;
        this.tutorAz = tutorAz;
        this.dataStipula = dataStipula;
        this.direttore=direttore;
    }


    public void setFirmaStudente(Bitmap firmaStudente) {
        this.firmaStudente = firmaStudente;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public TutorAc getTutorAc() {
        return tutorAc;
    }

    public void setTutorAc(TutorAc tutorAc) {
        this.tutorAc = tutorAc;
    }

    public TutorAz getTutorAz() {
        return tutorAz;
    }

    public void setTutorAz(TutorAz tutorAz) {
        this.tutorAz = tutorAz;
    }

    public Direttore getDirettore() {
        return direttore;
    }

    public void setDirettore(Direttore direttore) {
        this.direttore = direttore;
    }

    public String getListaObiettivi() {
        return listaObiettivi;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
