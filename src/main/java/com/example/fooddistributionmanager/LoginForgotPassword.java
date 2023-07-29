package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginForgotPassword {
    MainApplication main = new MainApplication();
    @FXML
    void returnLoginPage(ActionEvent event) {
        main.changeScene("Login.fxml");
    }

}
