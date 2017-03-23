package org.academiadecodigo.roothless;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.user.HibernateUserService;


/**
 * Created by codecadet on 07/03/17.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        HibernateUserService hibernateUserService = new HibernateUserService();

        ServiceRegistry.getInstanceService().addService(hibernateUserService);

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("registration_view");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
