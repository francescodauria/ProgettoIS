package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by User on 17/01/2018.
 */

public class Convenzione implements Serializable{

    private int id;
    private Azienda azienda;
    private Date dataStipula;
    private Direttore direttore;
    private String stato;

    /**
     *
     * @param azienda
     * @param dataStipula
     * @param direttore
     * @param stato
     */
    public Convenzione(Azienda azienda, Date dataStipula, Direttore direttore,String stato) {
        this.azienda = azienda;
        this.dataStipula=dataStipula;
        this.direttore=direttore;
        this.stato=stato;
    }

    /**
     *
     * @return
     */
    public String getStato() {
        return stato;
    }

    /**
     *
     * @param stato
     */
    public void setStato(String stato) {
        this.stato = stato;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
    public Date getDataStipula() {
        return dataStipula;
    }

    /**
     *
     * @param dataStipula
     */
    public void setDataStipula(Date dataStipula) {
        this.dataStipula = dataStipula;
    }

    /**
     *
     * @return
     */
    public Direttore getDirettore() {
        return direttore;
    }

    /**
     *
     * @param direttore
     */
    public void setDirettore(Direttore direttore) {
        this.direttore = direttore;
    }
}
