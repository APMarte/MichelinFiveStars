package org.academiadecodigo.roothless.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by codecadet on 13/03/17.
 */
public class ConnectionManager {

    private String dbUrl= "jdbc:mysql://localhost:3306/codecadets" + "?allowMultiQueries=true";
    private String user = "root";
    private String pass = "";

    Connection connection = null;

    public Connection getConnection() {

        try {
            if (connection == null) {
                connection = DriverManager.getConnection(dbUrl, user, pass);
            }
        } catch (SQLException ex) {
            System.out.println("Failure to connect to database : " + ex.getMessage());
        }
        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("Failure to close database connections: " + ex.getMessage());
        }
    }
}
