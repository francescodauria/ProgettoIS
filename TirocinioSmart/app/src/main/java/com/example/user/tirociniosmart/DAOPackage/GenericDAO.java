package com.example.user.tirociniosmart.DAOPackage;

/**
 * Created by User on 17/01/2018.
 */

public abstract class GenericDAO {

    private static GenericConnectionPool genericConnectionPool;


    public abstract void insert();



    public abstract void update();

    public abstract void search();

}
