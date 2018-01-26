package com.example.user.tirociniosmart.EntityPackage;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by User on 17/01/2018.
 */

public class Azienda implements Serializable{


    private String id;
    private String nome;
    private String email;
    private String sede;
    private String descrizione;
    private Bitmap logo;
    private String numeroTel;
    private Convenzione convenzione;
    private ArrayList<Obiettivo> listaObiettiviFormativi;

    /**
     *
     * @param id
     * @param nome
     * @param email
     * @param sede
     * @param descrizione
     * @param logo
     * @param numeroTel
     * @param listaObiettiviFormativi
     */
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

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param o
     */
    public void addObiettivoFormativo(Obiettivo o){
        listaObiettiviFormativi.add(o);
    }

    /**
     *
     * @param o
     */
    public void removeObiettivoFormativo(Obiettivo o){
       if( listaObiettiviFormativi.contains(o))
        listaObiettiviFormativi.remove(o);
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
    public String getSede() {
        return sede;
    }

    /**
     *
     * @param sede
     */
    public void setSede(String sede) {
        this.sede = sede;
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
    public Bitmap getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     */
    public void setLogo(Bitmap logo) {
        this.logo = logo;
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

    /**
     *
     * @return
     */
    public Convenzione getConvenzione() {
        return convenzione;
    }

    /**
     *
     * @param convenzione
     */
    public void setConvenzione(Convenzione convenzione) {
        this.convenzione = convenzione;
    }

    /**
     *
     * @return
     */
    public ArrayList<Obiettivo> getListaObiettiviFormativi() {
        return listaObiettiviFormativi;
    }

    /**
     *
     * @param listaObiettiviFormativi
     */
    public void setListaObiettiviFormativi(ArrayList<Obiettivo> listaObiettiviFormativi) {
        this.listaObiettiviFormativi = listaObiettiviFormativi;
    }
}
