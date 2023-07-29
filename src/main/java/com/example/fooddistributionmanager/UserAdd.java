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

public class UserAdd implements Initializable {
    List<User> list = new ArrayList<>();
    User user;

    @FXML
    private ChoiceBox<String> newAccountType;

    @FXML
    private Label AlertDisplay;
    @FXML
    private BorderPane borderPane;

    @FXML
    private TextField newEmail;

    @FXML
    private TextField newPassword;

    @FXML
    private TextField newPhone;

    @FXML
    private TextField newName;

    @FXML
    void saveButton(ActionEvent event) {
        if (newName.getText().equals("") || newPhone.getText().equals("") || newPassword.getText().equals("") || newEmail.getText().equals("")){
            AlertDisplay.setText("Empty field!");
            AlertDisplay.setStyle("-fx-text-fill: red;");
        } else {
            getUserInfo();
            saveUser();
            AlertDisplay.setText("Saved");
            AlertDisplay.setStyle("-fx-text-fill: green;");
            newEmail.setText(null);
            newPassword.setText(null);
            newPhone.setText(null);
            newName.setText(null);
        }

    }

    private void saveUser() {
        try {
            FileWriter fr = new FileWriter("src/main/resources/Data/users.txt");
            BufferedWriter writer = new BufferedWriter(fr);
            for (User u: list){
                writer.write(u.getEmail()+"&%&" + u.getPassword() + "&%&" + u.getName() + "&%&" + u.getAccount() + "&%&" + u.getPhone() + "\n");
            }
            writer.close();
            fr.close();

        } catch (Exception e){
            e.printStackTrace();
        }


    }

    private void getUserInfo() {
        user = new User();
        user.setName(newName.getText());
        user.setPhone(newPhone.getText());
        user.setPassword(newPassword.getText());
        user.setEmail(newEmail.getText());
        user.setAccount(newAccountType.getValue());
        list.add(user);
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

    private void setChoiceData() {
        newAccountType.getItems().addAll("admin","worker");
        newAccountType.setValue("worker");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
        setChoiceData();
    }
}
