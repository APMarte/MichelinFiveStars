package org.academiadecodigo.roothless.controller;

import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;

/**
 * Created by ricardo on 21-03-2017.
 */
public class Dashboard {

    private ObservableList<String> weekDays = FXCollections.observableArrayList();

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="pie_orders"
    private PieChart pie_orders; // Value injected by FXMLLoader

    @FXML // fx:id="day_status"
    private AreaChart<String, Integer> day_status; // Value injected by FXMLLoader

    @FXML // fx:id="week_view"
    private BarChart<String, Integer> week_view; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert pie_orders != null : "fx:id=\"pie_orders\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert day_status != null : "fx:id=\"day_status\" was not injected: check your FXML file 'dashboard.fxml'.";
        assert week_view != null : "fx:id=\"week_view\" was not injected: check your FXML file 'dashboard.fxml'.";

        // Obtém um array com nomes dos dias da semana em Inglês.
        String[] days = DateFormatSymbols.getInstance(Locale.ENGLISH).getWeekdays();
        // Converte o array numa lista e adiciona na ObservableList de dias da semana.
        weekDays.addAll(Arrays.asList(days));

        // Associa os nomes de mês como categorias para o eixo horizontal.
        //TODO: adiccionar uma propriedade xAxis a cada tabela
        //xAxis.setCategories(weekDays);

    }


}
