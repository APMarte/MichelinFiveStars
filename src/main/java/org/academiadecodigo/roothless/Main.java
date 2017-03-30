package org.academiadecodigo.roothless;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.roothless.model.dao.RoleDao;
import org.academiadecodigo.roothless.model.dao.UserDao;
import org.academiadecodigo.roothless.model.dao.hibernate.HibernateRoleDao;
import org.academiadecodigo.roothless.model.dao.hibernate.HibernateUserDao;
import org.academiadecodigo.roothless.persistence.TransactionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateSessionManager;
import org.academiadecodigo.roothless.persistence.hibernate.HibernateTransactionManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Created by codecadet on 07/03/17.
 */
public class Main extends Application {

    @Override
    public void init() {

        System.out.println(Thread.currentThread().getName());

        // Instantiate a user service
        UserDao userDao = new HibernateUserDao();
        RoleDao roleDao = new HibernateRoleDao();
        TransactionManager transactionManager = new HibernateTransactionManager();

        //Set the spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

//        UserService userService = new UserServiceHibernateDaoImplementation(userDao, transactionManager, roleDao);
//
//        ServiceRegistry.getInstanceService().addService(userService);

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
