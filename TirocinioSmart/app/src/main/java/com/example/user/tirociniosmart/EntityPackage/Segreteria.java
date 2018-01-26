package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;

/**
 * Created by User on 17/01/2018.
 */

public class Segreteria extends Utente implements Serializable{

    private String ruolo;

    /**
     *
     * @param username
     * @param password
     * @param ruolo
     */
    public Segreteria(String username, String password, String ruolo) {
        super(username, password);
        this.ruolo = ruolo;
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
}
