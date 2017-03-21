package org.academiadecodigo.roothless;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.persistence.ConnectionManager;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.user.JdbcUserService;
import org.academiadecodigo.roothless.service.user.MockUserService;
import org.academiadecodigo.roothless.util.Navigation;

/**
 * Created by codecadet on 07/03/17.
 */
public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        MockUserService mockUserService = new MockUserService();
        User user = new User();
        user.setUsername("Jesus");
        user.setPassword("MessiasSouEu");
        user.setEmail("cantseeme@heaven.domain");
        mockUserService.addUser(user);
        JdbcUserService jdbcUserService = new JdbcUserService(new ConnectionManager());

        ServiceRegistry.getInstanceService().addService(jdbcUserService);

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("userLogin");
        //Initializable controller = Navigation.getInstance().getController("userLogin");
        //((LoginController) controller).setService((UserService) ServiceRegistry.getInstanceService().getService("userService"));
    }



    public static void main(String[] args) {
        launch(args);
    }
}
