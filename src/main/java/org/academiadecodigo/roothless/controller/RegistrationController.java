package org.academiadecodigo.roothless.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by Paulo Rocha on 21-03-2017.
 */
public class RegistrationController {

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


    @FXML
    void login(ActionEvent event) {}

    @FXML
    void register(ActionEvent event) {}
}
