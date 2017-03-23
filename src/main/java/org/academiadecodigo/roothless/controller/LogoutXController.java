package org.academiadecodigo.roothless.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import org.academiadecodigo.roothless.Navigation;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by codecadet on 08/03/17.
 */
public class LogoutXController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem itemLogout;

    @FXML
    private MenuItem itemClose;

    @FXML
    void closeProgram(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void logout(ActionEvent event) {
        Navigation.getInstance().back();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assert itemLogout != null : "fx:id=\"itemLogout\" was not injected: check your FXML file 'userAccount.fxml'.";
        assert itemClose != null : "fx:id=\"itemClose\" was not injected: check your FXML file 'userAccount.fxml'.";

    }
}


