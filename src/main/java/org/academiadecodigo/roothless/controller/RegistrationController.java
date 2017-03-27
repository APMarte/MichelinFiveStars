package org.academiadecodigo.roothless.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.academiadecodigo.roothless.Navigation;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.user.UserService;
import org.academiadecodigo.roothless.utils.Security;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * Created by Paulo Rocha on 21-03-2017.
 */
public class RegistrationController implements Initializable {

    private static final String NAME = "login";

//    public void setService(UserService service) {
//        this.service = service;
//    }

    private boolean login = true;

    public static String getName() {
        return NAME;
    }

    @FXML
    Pane pane;

    //register and login buttons
    @FXML private Button register_button;
    @FXML private Button login_button;

    // User and password login fields
    @FXML private PasswordField password_login_field;
    @FXML private TextField username_login_field;

    //registration fields
    @FXML private TextField firstname_register_field;
    @FXML private TextField lastname_register_field;
    @FXML private TextField email_register_field;
    @FXML private TextField mobile_register_field;
    @FXML private TextField nif_register_field;
    @FXML private TextField password_register_field;
    @FXML private TextField password_verify_register_field;
    @FXML private TextField username_register_field;
    @FXML private TextField creditcard_register_field;
    @FXML private TextField expiration_register_field;
    @FXML private Label errorLabel;




        UserService userService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            userService = (UserService) ServiceRegistry.getInstanceService().getService(UserService.class.getSimpleName());


        assert username_login_field != null : "fx:id=\"username_login_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert password_login_field != null : "fx:id=\"password_login_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert login_button != null : "fx:id=\"login_button\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert email_register_field != null : "fx:id=\"email_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert register_button != null : "fx:id=\"register_button\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert mobile_register_field != null : "fx:id=\"mobile_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert nif_register_field != null : "fx:id=\"nif_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert password_verify_register_field != null : "fx:id=\"password_verify_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert password_register_field != null : "fx:id=\"password_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert username_register_field != null : "fx:id=\"username_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert creditcard_register_field != null : "fx:id=\"creditcard_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert expiration_register_field != null : "fx:id=\"expiration_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert lastname_register_field != null : "fx:id=\"lastname_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
        assert firstname_register_field != null : "fx:id=\"firstname_register_field\" was not injected: check your FXML file 'registration_view.fxml'.";
    }


    private void doLogin() {

        if (username_login_field.getText().isEmpty()) {
            showConsoleText("username missing");
            return;
        }

        if (password_login_field.getText().isEmpty()) {
            showConsoleText("password missing");
            return;
        }

        if (!userService.authenticate(username_login_field.getText(), password_login_field.getText())) {
            showConsoleText("authentication failed");
            return;
        }

        showConsoleText("login accepted");
        Navigation.getInstance().loadScreen("credits_view.fxml");

    }



    private void doRegister() {

        if (username_register_field.getText().isEmpty()) {
            showConsoleText("username missing");
            return;
        }

        if (password_register_field.getText().isEmpty()) {
            showConsoleText("password missing");
            return;
        }

        if (email_register_field.getText().isEmpty()) {
            showConsoleText("email missing");
            return;
        }

        if (userService.findByName(username_register_field.getText()) != null) {
            showConsoleText("username taken");
            return;
        }

        userService.addUser(new User());
                //.addUser(new User(username_register_field.getText(), Security.getHash(password_register_field .getText()), email_register_field.getText(), "client"));

        if (userService.findByName(username_register_field.getText()) == null) {
            showConsoleText("registration failed");
            return;
        }

        showConsoleText("registration successful");

    }

    private void showConsoleText(String text) {

        errorLabel.setText("console.log(\"" + text + "\");");
        errorLabel.setVisible(true);

    }

    public void login_button_pressed(ActionEvent event) {

        if (login) {
            doLogin();
        } else {
            doRegister();
        }

    }

    public void register_button_pressed(MouseEvent event) {
        doRegister();
        }

    }
