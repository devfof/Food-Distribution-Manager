package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Dashboard implements Initializable {
    @FXML
    private ChoiceBox<String> setDivision;

    @FXML
    private BorderPane borderPane;

    private void setChoiceData() {
        setDivision.getItems().addAll("Barisal","Chittagong","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet");
        setDivision.setValue("Dhaka");
    }
    public void getFood(ActionEvent event){

    }

    public void refresh(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashboardShow.fxml"));
            Pane root = fxmlLoader.load();
            borderPane.setCenter(root);

            DashboardShow controller =fxmlLoader.getController();
            controller.show(setDivision.getValue());

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChoiceData();
        refresh();
        setDivision.setOnAction(this::setShow);
    }

    private void setShow(ActionEvent actionEvent) {
        refresh();
    }


}