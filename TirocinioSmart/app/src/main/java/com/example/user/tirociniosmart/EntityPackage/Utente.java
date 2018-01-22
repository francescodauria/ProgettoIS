package com.example.user.tirociniosmart.EntityPackage;

import java.io.Serializable;

/**
 * Created by User on 17/01/2018.
 */

public class Utente implements Serializable{
    private String username;
    private String password;
 //   private String ruolo;
    public Utente(String username, String password){
     //   this.ruolo=ruolo;
        this.username=username;
        this.password=password;

}

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
