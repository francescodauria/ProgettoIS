package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;
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
    private String dataNascita;
    private int numeroTirocini;
    private String numeroTel;
    private ArrayList<ProgFormativo> listaProgettiFormativi;


    public Studente(String username, String password, String ruolo, String matricola, String nome, String cognome, String CF, String email, String indirizzo, String luogoNascita, String dataNascita, int numeroTirocini, String numeroTel, ArrayList<ProgFormativo> listaProgettiFormativi) {
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

    public String getRuolo() {
        return ruolo;
    }

    public String getMatricola() {
        return matricola;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getCF() {
        return CF;
    }

    public String getEmail() {
        return email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public int getNumeroTirocini() {
        return numeroTirocini;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public ArrayList<ProgFormativo> getListaProgettiFormativi() {
        return listaProgettiFormativi;
    }


    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setNumeroTirocini(int numeroTirocini) {
        this.numeroTirocini = numeroTirocini;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public void setListaProgettiFormativi(ArrayList<ProgFormativo> listaProgettiFormativi) {
        this.listaProgettiFormativi = listaProgettiFormativi;
    }
}
