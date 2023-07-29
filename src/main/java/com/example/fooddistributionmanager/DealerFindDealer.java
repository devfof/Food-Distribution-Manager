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
import java.io.FileReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DealerFindDealer implements Initializable {
    MainApplication mainApp;
    List<Dealer> list = new ArrayList<>();
    Dealer dealer;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label alertDisplay;

    @FXML
    private TextField findID;

    @FXML
    void FindButton(ActionEvent event) {
        if (findID.getText().equals("")){
            alertDisplay.setText("Empty field!");
            alertDisplay.setStyle("-fx-text-fill: red;");
        } else{
            findUser();
        }

    }

    private void findUser() {
        mainApp = new MainApplication();
        for (Dealer d: list){
            if (d.getId().equals(findID.getText())){
                mainApp.setFoundUser(d.getId());
                alertDisplay.setText("User Found");
                alertDisplay.setStyle("-fx-text-fill: green;");
                changeView("DealerEdit.fxml");
            }
            else {
                alertDisplay.setText("User not Found");
                alertDisplay.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private void getUser() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/dealers.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("&%&");
                dealer = new Dealer();
                dealer.setName(parts[0]);
                dealer.setId(parts[1]);
                dealer.setDivision(parts[2]);
                dealer.setPhone(parts[3]);
                list.add(dealer);
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
;
        getUser();
    }


}
