package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SwitchMode {

    MainApplication main = new MainApplication();
    @FXML
    void adminBTN(ActionEvent event) {
        main.changeScene("MainAdmin.fxml");
    }

    @FXML
    void loginBTN(ActionEvent event) {
        main.changeScene("MainWorker.fxml");
    }

}
