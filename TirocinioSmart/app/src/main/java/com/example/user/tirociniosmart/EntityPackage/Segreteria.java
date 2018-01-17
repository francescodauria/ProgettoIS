package com.example.user.tirociniosmart.EntityPackage;

/**
 * Created by User on 17/01/2018.
 */

public class Segreteria extends Utente {

    private String ruolo;


    public Segreteria(String username, String password, String ruolo) {
        super(username, password);
        this.ruolo = ruolo;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
