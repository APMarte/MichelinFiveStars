package org.academiadecodigo.roothless.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import org.academiadecodigo.roothless.model.User;
import org.academiadecodigo.roothless.service.ServiceRegistry;
import org.academiadecodigo.roothless.service.user.UserService;
import org.academiadecodigo.roothless.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 07/03/17.
 */
public class LoginController implements Initializable {

    private UserService service;

    public void setService(UserService service) {
        this.service = service;
    }

    @FXML
    private TextField userField;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    private TextField mailField;

    @FXML
    private Hyperlink register;

    @FXML
    private Label message;


    @FXML
    void login(ActionEvent event) {

        if (loginButton.getText().equals("Login")) {
            if (service.authenticate(userField.getText(), password.getText())) {
                message.setText("Welcome");
                message.setTextFill(Color.GREEN);
                message.setVisible(true);
                Navigation.getInstance().loadScreen("userAccount");
                Navigation.getInstance().getController("userAccount");
            } else {
                message.setTextFill(Color.RED);
                message.setText("Invalid User, please try again");
                message.setVisible(true);
            }
        }

        if(loginButton.getText().equals("Sign in")) {
            if (service.findByName(userField.getText()) != null) {
                message.setTextFill(Color.RED);
                message.setText("User already exists");
            } else {
                if (userField.getText().equals("")) {
                    message.setTextFill(Color.RED);
                    message.setText("A username must be inserted");
                } else {
                    if (mailField.getText().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                        service.addUser(new User(userField.getText(), mailField.getText(), password.getText(), "client"));
                        System.out.println(service.findByName(userField.getText()));
                    } else {
                        message.setTextFill(Color.RED);
                        message.setText("Invalid E-mail, please reinsert your email");
                    }
                }
                message.setVisible(false);
            }
        }

    }

    public UserService getService() {
        return service;
    }

    @FXML
    void registerUser(ActionEvent event) {

        if (!mailField.isVisible()) {
            loginButton.setText("Sign in");
            mailField.setVisible(true);
            register.setText("Cancel");
        } else {
            loginButton.setText("Login");
            register.setText("Register");
            mailField.setVisible(false);
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert userField != null : "fx:id=\"userField\" was not injected: check your FXML file 'userLogin.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'userLogin.fxml'.";
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'userLogin.fxml'.";
        assert mailField != null : "fx:id=\"mailField\" was not injected: check your FXML file 'userLogin.fxml'.";
        assert register != null : "fx:id=\"register\" was not injected: check your FXML file 'userLogin.fxml'.";

        mailField.setVisible(false);
        message.setVisible(false);

        setService((UserService)ServiceRegistry.getInstanceService().getService(UserService.class.getSimpleName()));
    }
}


