package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class DealerManager {

    @FXML
    private BorderPane borderPane;

    @FXML
    void removeDealer(ActionEvent event) {
        changeView("DealerRemove.fxml");
    }

    @FXML
    void addDealer(ActionEvent event) {
        changeView("DealerAdd.fxml");
    }

    @FXML
    void showDealer(ActionEvent event) {
        changeView("DealerShow.fxml");
    }

    @FXML
    void editDealer(ActionEvent event) {
        changeView("DealerFindDealer.fxml");
    }
    public void changeView(String fxml){
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxml));
            borderPane.setCenter(root);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
