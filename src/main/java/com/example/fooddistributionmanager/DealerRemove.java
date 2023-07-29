package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DealerRemove implements Initializable {
    List<Dealer> list = new ArrayList<>();
    Dealer dealer;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label alertDisplay;

    @FXML
    private TextField findId;

    @FXML
    void removeButton(ActionEvent event) {
        if (findId.getText().equals("")){
            alertDisplay.setText("Empty field!");
            alertDisplay.setStyle("-fx-text-fill: red;");
        } else{
            findUser();
            saveUser();
        }

    }

    private void findUser() {
        for (Dealer d: list){
            if (d.getId().equals(findId.getText())){
                removeUser(d.getId());
                alertDisplay.setText("User Removed!!");
                alertDisplay.setStyle("-fx-text-fill: green;");
                break;
            }
            else {
                alertDisplay.setText("User not Found");
                alertDisplay.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private void removeUser(String id) {
        for (Dealer d: list) {
            if (id.equals(d.getId())){
                list.remove(d);
                break;
            }
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
    }
}
