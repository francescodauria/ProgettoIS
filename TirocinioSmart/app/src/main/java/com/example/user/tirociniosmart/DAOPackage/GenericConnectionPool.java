package com.example.user.tirociniosmart.DAOPackage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by User on 17/01/2018.
 */

public interface GenericConnectionPool {
    /**
     *
     * @return
     * @throws SQLException
     */
    public Connection createDBConnection() throws SQLException;
        public Connection getConnection() throws SQLException ;
        public void releaseConnection(Connection connection) throws SQLException ;

}
