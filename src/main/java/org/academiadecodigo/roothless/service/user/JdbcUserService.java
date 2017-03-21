package org.academiadecodigo.roothless.service.user;

import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.persistence.ConnectionManager;

import java.sql.*;

/**
 * Created by codecadet on 13/03/17.
 */
public class JdbcUserService implements UserService {


    private Connection dbConnection;
    private ConnectionManager connectionManager;


    public JdbcUserService(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        dbConnection=connectionManager.getConnection();
    }

    @Override
    public boolean authenticate(String name, String password) {

        return findByName(name) != null && findByName(name).getPassword().equals(password);

    }

    @Override
    public void addUser(User user) {

        if(dbConnection==null){
            dbConnection=connectionManager.getConnection();
        }

        PreparedStatement statement;

        String username = user.getUsername();
        String email = user.getEmail();
        String password = user.getPassword();

        try {
            String query = "INSERT INTO User(username, email, password) VALUES (?,?,?)";
            statement = dbConnection.prepareStatement(query);
            statement.setString(1,username);
            statement.setString(2,email);
            statement.setString(3,password);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User findByName(String name) {

        if(dbConnection==null){
            dbConnection=connectionManager.getConnection();
        }

        PreparedStatement statement;
        User user = null;

        try {

            String query = "SELECT * FROM User WHERE username = ?";

            statement = dbConnection.prepareStatement(query);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            // user exists
            if (resultSet.next()) {

                String usernameValue = resultSet.getString("username");
                String passwordValue = resultSet.getString("password");
                String emailValue = resultSet.getString("email");

                user = new User(usernameValue, emailValue, passwordValue);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;

    }

    @Override
    public int count() {

        if(dbConnection==null){
            dbConnection=connectionManager.getConnection();
        }
        int result = 0;
        Statement statement = null;

        try {
            // create a new statement
            statement = dbConnection.createStatement();

            // create a query
            String query = "SELECT COUNT(*) FROM User";

            // execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // get the results

            if (resultSet.next()) {
                result = resultSet.getInt(1);
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    public String getType(){
        return UserService.class.getSimpleName();
    }
}
