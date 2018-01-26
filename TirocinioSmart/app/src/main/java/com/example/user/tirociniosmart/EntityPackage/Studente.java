package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class Studente extends Utente implements Serializable{

    private String ruolo;
    private String matricola;
    private String nome;
    private String cognome;
    private String CF;
    private String email;
    private String indirizzo;
    private String luogoNascita;
    private Date dataNascita;
    private int numeroTirocini;
    private String numeroTel;
    private ArrayList<ProgFormativo> listaProgettiFormativi;

    /**
     *
     * @param username
     * @param password
     * @param ruolo
     * @param matricola
     * @param nome
     * @param cognome
     * @param CF
     * @param email
     * @param indirizzo
     * @param luogoNascita
     * @param dataNascita
     * @param numeroTirocini
     * @param numeroTel
     * @param listaProgettiFormativi
     */
    public Studente(String username, String password, String ruolo, String matricola, String nome, String cognome, String CF, String email, String indirizzo, String luogoNascita, Date dataNascita, int numeroTirocini, String numeroTel, ArrayList<ProgFormativo> listaProgettiFormativi) {
        super(username, password);
        this.ruolo = ruolo;
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.email = email;
        this.indirizzo = indirizzo;
        this.luogoNascita = luogoNascita;
        this.dataNascita = dataNascita;
        this.numeroTirocini = numeroTirocini;
        this.numeroTel = numeroTel;
        this.listaProgettiFormativi = listaProgettiFormativi;
    }

    /**
     *
     * @return
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     *
     * @return
     */
    public String getMatricola() {
        return matricola;
    }

    /**
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @return
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @return
     */
    public String getCF() {
        return CF;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @return
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     *
     * @return
     */
    public String getLuogoNascita() {
        return luogoNascita;
    }

    /**
     *
     * @return
     */
    public Date getDataNascita() {
        return dataNascita;
    }

    /**
     *
     * @return
     */
    public int getNumeroTirocini() {
        return numeroTirocini;
    }

    /**
     *
     * @return
     */
    public String getNumeroTel() {
        return numeroTel;
    }

    /**
     *
     * @return
     */
    public ArrayList<ProgFormativo> getListaProgettiFormativi() {
        return listaProgettiFormativi;
    }

    /**
     *
     * @param ruolo
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    /**
     *
     * @param matricola
     */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     *
     * @param CF
     */
    public void setCF(String CF) {
        this.CF = CF;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @param indirizzo
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     *
     * @param luogoNascita
     */
    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    /**
     *
     * @param dataNascita
     */
    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     *
     * @param numeroTirocini
     */
    public void setNumeroTirocini(int numeroTirocini) {
        this.numeroTirocini = numeroTirocini;
    }

    /**
     *
     * @param numeroTel
     */
    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    /**
     *
     * @param listaProgettiFormativi
     */
    public void setListaProgettiFormativi(ArrayList<ProgFormativo> listaProgettiFormativi) {
        this.listaProgettiFormativi = listaProgettiFormativi;
    }
}
