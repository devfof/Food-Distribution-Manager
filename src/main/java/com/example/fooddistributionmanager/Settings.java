package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Settings implements Initializable {
    MainApplication main = new MainApplication();

    @FXML
    private Label userAccount;

    @FXML
    private Label userEmail;

    @FXML
    private Label username;

    @FXML
    private Label userPhone;
    @FXML
    void Logout(ActionEvent event) {

        main.changeScene("Login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User user = main.getCurrentUser();
        userAccount.setText(user.getAccount());
        userEmail.setText(user.getEmail());
        username.setText(user.getName());
        userPhone.setText(user.getPhone());
    }
}
