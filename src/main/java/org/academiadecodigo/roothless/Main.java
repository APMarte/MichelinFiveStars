package org.academiadecodigo.roothless;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.model.dao.hibernate.HibernateUserDao;
import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;
import org.academiadecodigo.roothless.persistence.jdbc.ConnectionManager;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.jdbc.JdbcUserService;
import org.academiadecodigo.roothless.service.user.HibernateUserService;
import org.academiadecodigo.roothless.service.user.UserService;
import org.academiadecodigo.roothless.service.user.UserServiceImpl;


/**
 * Created by codecadet on 07/03/17.
 */
public class Main extends Application {


    private ConnectionManager connectionManager;

    @Override
    public void init() {

        System.out.println(Thread.currentThread().getName());

        connectionManager = new ConnectionManager();

        // Instantiate a user service
        UserDao userDao = new HibernateUserDao();
        TransactionManager transactionManager = new HibernateTransactionManager();
        UserService userService = new UserServiceImpl(userDao, transactionManager);
    }

        @Override
        public void start (Stage primaryStage)throws Exception {

            primaryStage.setTitle("Five Star Reservations");

            HibernateUserService hibernateUserService = new HibernateUserService();

            ServiceRegistry.getInstanceService().addService(hibernateUserService);

            Navigation.getInstance().setStage(primaryStage);
            Navigation.getInstance().loadScreen("registration_view");

            primaryStage.show();
        }

    public static void main(String[] args) {
        launch(args);
    }
}
