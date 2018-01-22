package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;

/**
 * Created by User on 17/01/2018.
 */

public class TutorAz extends Utente implements Serializable{


    private String ruolo;
    private String nome;
    private String cognome;
    private String CF;
    private String email;
    private String numeroTel;
    private Azienda azienda;

    public TutorAz(String username, String password, String ruolo, String nome, String cognome, String CF, String email, String numeroTel, Azienda azienda) {
        super(username, password);
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome = cognome;
        this.CF = CF;
        this.email = email;
        this.numeroTel = numeroTel;
        this.azienda = azienda;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCF() {
        return CF;
    }

    public void setCF(String CF) {
        this.CF = CF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
}
