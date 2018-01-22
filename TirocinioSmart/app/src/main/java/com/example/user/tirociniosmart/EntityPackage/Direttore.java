package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;

/**
 * Created by User on 17/01/2018.
 */

public class Direttore extends Utente implements Serializable{

    private String ruolo;
    private String matricola;
    private String nome;
    private String cognome;

    public Direttore(String username, String password, String ruolo, String matricola, String nome, String cognome) {
        super(username, password);
        this.ruolo = ruolo;
        this.matricola = matricola;
        this.nome = nome;
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
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
}
