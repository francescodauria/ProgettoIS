package com.example.user.tirociniosmart.EntityPackage;

/**
 * Created by User on 17/01/2018.
 */

public class Obiettivo {

    private String nome;
    private String descrizione;
    private Azienda azienda;

    public Obiettivo(String nome, String descrizione, Azienda azienda) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.azienda = azienda;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }
}
