package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class ProgFormativo implements Serializable{

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

    /**
     *
     * @param stato
     * @param motivazione
     * @param numeroOre
     * @param listaObiettivi
     * @param dataInizio
     * @param dataFine
     * @param dataStipula
     * @param firmaStudente
     * @param studente
     * @param dir
     * @param tutorAc
     * @param tutorAz
     */
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
        this.direttore=dir;
    }

    /**
     *
     * @param firmaStudente
     */
    public void setFirmaStudente(Bitmap firmaStudente) {
        this.firmaStudente = firmaStudente;
    }

    /**
     *
     * @return
     */
    public Studente getStudente() {
        return studente;
    }

    /**
     *
     * @param studente
     */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     *
     * @return
     */
    public TutorAc getTutorAc() {
        return tutorAc;
    }

    /**
     *
     * @param tutorAc
     */
    public void setTutorAc(TutorAc tutorAc) {
        this.tutorAc = tutorAc;
    }

    /**
     *
     * @return
     */
    public TutorAz getTutorAz() {
        return tutorAz;
    }

    /**
     *
     * @param tutorAz
     */
    public void setTutorAz(TutorAz tutorAz) {
        this.tutorAz = tutorAz;
    }

    /**
     *
     * @return
     */
    public Direttore getDirettore() {
        return direttore;
    }

    /**
     *
     * @param direttore
     */
    public void setDirettore(Direttore direttore) {
        this.direttore = direttore;
    }

    /**
     *
     * @return
     */
    public String getListaObiettivi() {
        return listaObiettivi;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param listaObiettivi
     */
    public void setListaObiettivi(String listaObiettivi) {
        this.listaObiettivi = listaObiettivi;
    }

    /**
     *
     * @return
     */
    public Bitmap getFirmaDirettore() {
        return firmaDirettore;
    }

    /**
     *
     * @param firmaDirettore
     */
    public void setFirmaDirettore(Bitmap firmaDirettore) {
        this.firmaDirettore = firmaDirettore;
    }

    /**
     *
     * @return
     */
    public String getStato() {
        return stato;
    }

    /**
     *
     * @param stato
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     *
     * @return
     */
    public String getMotivazione() {
        return motivazione;
    }

    /**
     *
     * @param motivazione
     */
    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    /**
     *
     * @return
     */
    public int getNumeroOre() {
        return numeroOre;
    }

    /**
     *
     * @param numeroOre
     */
    public void setNumeroOre(int numeroOre) {
        this.numeroOre = numeroOre;
    }

    /**
     *
     * @return
     */
    public Bitmap getFirmaTutorAcc() {
        return firmaTutorAcc;
    }

    /**
     *
     * @param firmaTutorAcc
     */
    public void setFirmaTutorAcc(Bitmap firmaTutorAcc) {
        this.firmaTutorAcc = firmaTutorAcc;
    }

    /**
     *
     * @return
     */
    public Bitmap getGetFirmaTutorAz() {
        return getFirmaTutorAz;
    }

    /**
     *
     * @param getFirmaTutorAz
     */
    public void setGetFirmaTutorAz(Bitmap getFirmaTutorAz) {
        this.getFirmaTutorAz = getFirmaTutorAz;
    }

    /**
     *
     * @return
     */
    public Date getDataInizio() {
        return dataInizio;
    }

    /**
     *
     * @param dataInizio
     */
    public void setDataInizio(Date dataInizio) {
        this.dataInizio = dataInizio;
    }

    /**
     *
     * @return
     */
    public Date getDataFine() {
        return dataFine;
    }

    /**
     *
     * @param dataFine
     */
    public void setDataFine(Date dataFine) {
        this.dataFine = dataFine;
    }

    /**
     *
     * @return
     */
    public Date getDataStipula() {
        return dataStipula;
    }

    /**
     *
     * @param dataStipula
     */
    public void setDataStipula(Date dataStipula) {
        this.dataStipula = dataStipula;
    }

    /**
     *
     * @return
     */
    public Bitmap getFirmaStudente() {
        return firmaStudente;
    }
}
