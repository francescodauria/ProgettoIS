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

    /**
     *
     * @param username
     * @param password
     * @param ruolo
     * @param nome
     * @param cognome
     * @param CF
     * @param email
     * @param numeroTel
     * @param azienda
     */
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

    /**
     *
     * @return
     */
    public Azienda getAzienda() {
        return azienda;
    }

    /**
     *
     * @param azienda
     */
    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
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
     * @param ruolo
     */
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
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
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
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
     * @param cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
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
     * @param CF
     */
    public void setCF(String CF) {
        this.CF = CF;
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
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @param numeroTel
     */
    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
}
