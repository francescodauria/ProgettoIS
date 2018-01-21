package com.example.user.tirociniosmart.DAOPackage;

/**
 * Created by User on 17/01/2018.
 */

public class StudenteDAO extends GenericDAO {

    private static GenericConnectionPool genericConnectionPool;

    public static void setConnectionPool(GenericConnectionPool connectionPool){
        genericConnectionPool = connectionPool;
    }


    public static boolean insert() {
        return true;
    }

    public static void update() {
    }

    public static void search() {
    }

}
