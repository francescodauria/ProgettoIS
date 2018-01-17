package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class Azienda {


    private String id;
    private String nome;
    private String email;
    private String sede;
    private String descrizione;
    private Bitmap logo;
    private String numeroTel;
    private Convenzione convenzione;
    private ArrayList<Obiettivo> listaObiettiviFormativi;

    public Azienda(String id, String nome, String email, String sede, String descrizione, Bitmap logo, String numeroTel, ArrayList<Obiettivo> listaObiettiviFormativi) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.sede = sede;
        this.descrizione = descrizione;
        this.logo = logo;
        this.numeroTel = numeroTel;
        this.listaObiettiviFormativi = listaObiettiviFormativi;
    }

    public String getId() {
        return id;
    }

    public void addObiettivoFormativo(Obiettivo o){
        listaObiettiviFormativi.add(o);
    }

    public void removeObiettivoFormativo(Obiettivo o){
       if( listaObiettiviFormativi.contains(o))
        listaObiettiviFormativi.remove(o);

    }


    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Bitmap getLogo() {
        return logo;
    }

    public void setLogo(Bitmap logo) {
        this.logo = logo;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public Convenzione getConvenzione() {
        return convenzione;
    }

    public void setConvenzione(Convenzione convenzione) {
        this.convenzione = convenzione;
    }

    public ArrayList<Obiettivo> getListaObiettiviFormativi() {
        return listaObiettiviFormativi;
    }

    public void setListaObiettiviFormativi(ArrayList<Obiettivo> listaObiettiviFormativi) {
        this.listaObiettiviFormativi = listaObiettiviFormativi;
    }
}
