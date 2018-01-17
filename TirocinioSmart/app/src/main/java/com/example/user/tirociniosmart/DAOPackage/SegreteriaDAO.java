package com.example.user.tirociniosmart.DAOPackage;

/**
 * Created by User on 17/01/2018.
 */

public class SegreteriaDAO extends GenericDAO {
    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }


    @Override
    public void insert() {
    }

    @Override
    public void update() {
    }

    @Override
    public void search() {
    }
}
