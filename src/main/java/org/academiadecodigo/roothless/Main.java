package org.academiadecodigo.roothless;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.roothless.model.Role;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.RoleDao;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.model.dao.hibernate.HibernateRoleDao;
import org.academiadecodigo.roothless.model.dao.hibernate.HibernateUserDao;
import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;
import org.academiadecodigo.roothless.persistence.jdbc.ConnectionManager;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.user.HibernateUserService;
import org.academiadecodigo.roothless.service.user.UserService;
import org.academiadecodigo.roothless.service.user.UserServiceHibernateDaoImplementation;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by codecadet on 07/03/17.
 */
public class Main extends Application {


    // private ConnectionManager connectionManager; // Just needed for jdbc

    @Override
    public void init() {

        System.out.println(Thread.currentThread().getName());

        // connectionManager = new ConnectionManager(); // Just needed for jdbc

        // Instantiate a user service
        UserDao userDao = new HibernateUserDao();
        RoleDao roleDao = new HibernateRoleDao();
        TransactionManager transactionManager = new HibernateTransactionManager();

        UserService userService = new UserServiceHibernateDaoImplementation(userDao, transactionManager, roleDao);

        ServiceRegistry.getInstanceService().addService(userService);

        // initiate some roles and users for testing purposes

//        Set<Role> roles = new HashSet<>();
//
//        roles.add(new Role("admin"));
//        roles.add(new Role("user"));
//        roles.add(new Role("client"));
//
//        User user = new User("admin", "abc@gmail.com", "admin" , "admin");
//
//        userService.addUser(user);
    }

    @Override
    public void start (Stage primaryStage)throws Exception {

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("registration_view");
        primaryStage.setTitle("Five Star Reservations");
        primaryStage.show();


    }


    @Override //Allows the app to quit
    public void stop() throws Exception {
        HibernateSessionManager.close();
        super.stop();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
