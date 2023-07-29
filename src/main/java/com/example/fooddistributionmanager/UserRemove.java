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

public class UserRemove implements Initializable {
    List<User> list = new ArrayList<>();
    User user;
    @FXML
    private BorderPane borderPane;
    @FXML
    private Label alertDisplay;

    @FXML
    private TextField findEmail;

    @FXML
    void removeButton(ActionEvent event) {
        if (findEmail.getText().equals("")){
            alertDisplay.setText("Empty field!");
            alertDisplay.setStyle("-fx-text-fill: red;");
        } else{
            findUser();
            saveUser();
        }

    }

    private void findUser() {
        for (User u : list){
            if (u.getEmail().equals(findEmail.getText())){
                removeUser(u.getEmail());
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

    private void removeUser(String email) {
        for (User u : list) {
            if (email.equals(u.getEmail())){
                list.remove(u);
                break;
            }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getUser();
    }
}
