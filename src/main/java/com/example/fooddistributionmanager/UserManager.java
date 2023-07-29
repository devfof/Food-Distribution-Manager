package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class UserManager {

    @FXML
    private BorderPane borderPane;

    @FXML
    void removeUser(ActionEvent event) {
        changeView("UserRemove.fxml");
    }

    @FXML
    void addUser(ActionEvent event) {
        changeView("UserAdd.fxml");
    }

    @FXML
    void showUser(ActionEvent event) {
        changeView("UserShow.fxml");
    }

    @FXML
    void editUser(ActionEvent event) {
        changeView("UserFindUser.fxml");
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
