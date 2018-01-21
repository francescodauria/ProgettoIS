package com.example.user.tirociniosmart.DAOPackage;

import java.sql.SQLException;

/**
 * Created by User on 17/01/2018.
 */

public abstract class GenericDAO {

    private static GenericConnectionPool genericConnectionPool;


    public static boolean insert()throws SQLException {return true;} ;



    public static void update(){};

    public static void search(){};

}
