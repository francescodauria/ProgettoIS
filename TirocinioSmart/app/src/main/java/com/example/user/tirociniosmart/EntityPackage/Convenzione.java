package com.example.user.tirociniosmart.EntityPackage;

import java.sql.Date;

/**
 * Created by User on 17/01/2018.
 */

public class Convenzione {

    private String id;
    private Azienda azienda;
    private Date dataStipula;
    private Direttore direttore;
    private String stato;

    public Convenzione(String id, Azienda azienda, Date dataStipula, Direttore direttore,String stato) {
        this.id = id;
        this.azienda = azienda;
        this.dataStipula=dataStipula;
        this.direttore=direttore;
        this.stato=stato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public Date getDataStipula() {
        return dataStipula;
    }

    public void setDataStipula(Date dataStipula) {
        this.dataStipula = dataStipula;
    }

    public Direttore getDirettore() {
        return direttore;
    }

    public void setDirettore(Direttore direttore) {
        this.direttore = direttore;
    }
}
