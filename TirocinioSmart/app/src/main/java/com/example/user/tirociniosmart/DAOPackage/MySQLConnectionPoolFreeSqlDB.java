package com.example.user.tirociniosmart.DAOPackage;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 17/01/2018.
 */

public class MySQLConnectionPoolFreeSqlDB implements  GenericConnectionPool {

    private static List<Connection> freeDbConnections;
    static
    {
        freeDbConnections = new LinkedList<Connection>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DB driver not found!" + e);
        }
    }



    @Override
    public Connection createDBConnection() throws SQLException {
        Connection newConnection = null;

        String server2 = "65.19.141.67";
        String db2 = "tirocini_tirociniosmart";
        String username2 = "tirocini_utente";
        String password2 = "Password96";

        String server = "sql11.freemysqlhosting.net";
        String db = "sql11202205";
        String username = "sql11202205";
        String password = "Qfp6CVZqmG";

        newConnection = DriverManager.getConnection("jdbc:mysql://"+server+":3306/"+db,username,password);
        newConnection.setAutoCommit(false);
        return newConnection;


    }

    @Override
    public synchronized Connection getConnection() throws SQLException {
        Connection connection;
        if (! freeDbConnections.isEmpty()) {
            connection = (Connection) freeDbConnections.get(0);
            this.freeDbConnections.remove(0);
            try
            {
                if(connection!=null)
                {
                    if (connection.isClosed())
                        connection = this.getConnection();
                }
            }
            catch (SQLException e)
            {
                connection.close();
                connection = this.getConnection();
            }
        } else connection = this.createDBConnection();
        return connection;

    }

    @Override
    public void releaseConnection(Connection connection) {
        if (connection != null)
            this.freeDbConnections.add(connection);

    }

    public void closeAllConnection() throws SQLException {
        for(Connection con:freeDbConnections) {
            con.close();
        }
        freeDbConnections.clear();
    }


}

