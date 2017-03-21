package org.academiadecodigo.roothless.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

/**
 * Created by tlourenzo on 21-03-2017.
 */
public class KitchenController {
    @FXML private Menu button_reserves;
    @FXML private Menu button_menus;
    @FXML private Menu button_kitchen;
    @FXML private Menu button_management;
    @FXML private Menu button_dashboard;
    @FXML private TableColumn reservations_daily_list;
    @FXML private TableColumn reservation_dishes_list;
    @FXML private ProgressIndicator reservation_cooking_indicator;
    @FXML private Label day_label;
    @FXML private Button button_cooking_done;

    public void conclude_cooking(ActionEvent actionEvent) {
    }

    public void choosen_reservation(TableColumn.CellEditEvent cellEditEvent) {

    }

    public void choose_dashboard(ActionEvent actionEvent) {
    }

    public void choose_management(ActionEvent actionEvent) {
    }

    public void choose_kitchen(ActionEvent actionEvent) {
    }

    public void choose_menus(ActionEvent actionEvent) {
    }

    public void choose_reserves(ActionEvent actionEvent) {
    }
}
