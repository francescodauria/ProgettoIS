package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;

/**
 * Created by User on 17/01/2018.
 */

public class Obiettivo implements Serializable{

    private String nome;
    private String descrizione;
    private Azienda azienda;

    /**
     *
     * @param nome
     * @param descrizione
     * @param azienda
     */
    public Obiettivo(String nome, String descrizione, Azienda azienda) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.azienda = azienda;
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
    public String getDescrizione() {
        return descrizione;
    }

    /**
     *
     * @param descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
}
