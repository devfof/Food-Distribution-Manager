package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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

public class DealerEdit implements Initializable {
    List<Dealer> list = new ArrayList<>();
    Dealer dealer;
    MainApplication mainApp;


    @FXML
    private Label AlertDisplay;

    @FXML
    private ChoiceBox<String> newDivisionChoice;
    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField newId;

    @FXML
    private TextField newPhone;

    @FXML
    private TextField newName;

    @FXML
    void saveButton(ActionEvent event) {
        if (newName.getText().equals("") || newPhone.getText().equals("") || newId.getText().equals("")){
            AlertDisplay.setText("Empty field!");
            AlertDisplay.setStyle("-fx-text-fill: red;");
        } else {
            getUserInfo();
            saveUser();
            reset();
            changeView("DealerFindDealer.fxml");
            AlertDisplay.setText("Saved");
            AlertDisplay.setStyle("-fx-text-fill: green;");
        }

    }

    private void reset() {
        newId.setText(null);
        newPhone.setText(null);
        newName.setText(null);
        newDivisionChoice.setValue("Dhaka");
    }

    private void saveUser() {
        try {
            FileWriter fr = new FileWriter("src/main/resources/Data/dealers.txt");
            BufferedWriter writer = new BufferedWriter(fr);
            for (Dealer d: list){
                writer.write(d.getName()+"&%&" + d.getId() + "&%&" + d.getDivision() +  "&%&" + d.getPhone() + "\n");
            }
            writer.close();
            fr.close();

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    private void getUserInfo() {
        mainApp = new MainApplication();
        for (Dealer d: list){
            if (d.getId().equals(mainApp.getFoundUser())){
                d.setName(newName.getText());
                d.setPhone(newPhone.getText());
                d.setId(newId.getText());
                d.setDivision(newDivisionChoice.getValue());
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Pane root = fxmlLoader.load();
            borderPane.setCenter(root);
            borderPane.setTop(null);
            borderPane.setBottom(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);

            DealerFindDealer controller = fxmlLoader.getController();
            controller.setAlert("Saved Successfully");

        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void setChoiceData() {
        newDivisionChoice.getItems().addAll("Barisal","Chittagong","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet");
        newDivisionChoice.setValue("Dhaka");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
        setChoiceData();
    }


}
