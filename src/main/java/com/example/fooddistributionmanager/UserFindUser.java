package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class UserFindUser implements Initializable {
    MainApplication mainApp;
    List<User> list = new ArrayList<>();
    User user;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label alertDisplay;

    @FXML
    private TextField findEmail;

    @FXML
    void FindButton(ActionEvent event) {
        if (findEmail.getText().equals("")){
            alertDisplay.setText("Empty field!");
            alertDisplay.setStyle("-fx-text-fill: red;");
        } else{
            findUser();
        }

    }

    private void findUser() {
        mainApp = new MainApplication();
        for (User s: list){
            if (s.getEmail().equals(findEmail.getText())){
                mainApp.setFoundUser(s.getEmail());
                alertDisplay.setText("User Found");
                alertDisplay.setStyle("-fx-text-fill: green;");
                changeView("UserEdit.fxml");
            }
            else {
                alertDisplay.setText("User not Found");
                alertDisplay.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private void getUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/users.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("&%&");
                user = new User();
                user.setEmail(parts[0]);
                user.setPassword(parts[1]);
                user.setName(parts[2]);
                user.setAccount(parts[3]);
                user.setPhone(parts[4]);
                list.add(user);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeView(String fxml){
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxml));
            borderPane.setCenter(root);
            borderPane.setTop(null);
            borderPane.setBottom(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setAlert(String alert) {
        alertDisplay.setText(alert);
        alertDisplay.setStyle("-fx-text-fill: green;");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
    }
}
