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

public class UserEdit implements Initializable {
    MainApplication mainApp;
    String foundUser;
    List<User> list;
    User user;
    @FXML
    private Label AlertDisplay;


    @FXML
    private ChoiceBox<String> accountType;

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
            getUser();
            getUserInfo();
            saveUser();
            changeView("UserFindUser.fxml");
            AlertDisplay.setText("Saved");
            AlertDisplay.setStyle("-fx-text-fill: green;");
        }

    }

    private void saveUser() {
        try {
            FileWriter fr = new FileWriter("src/main/resources/Data/dealers.txt");
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
        mainApp = new MainApplication();
        for (User s: list){
            if (s.getEmail().equals(mainApp.getFoundUser())){
                s.setName(newName.getText());
                s.setPhone(newPhone.getText());
                s.setPassword(newPassword.getText());
                s.setEmail(newEmail.getText());
                s.setAccount(accountType.getValue());
            }
        }
    }
    private void setChoiceData() {
        accountType.getItems().addAll("Admin","Worker");
        accountType.setValue("Worker");
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

    private void getUser() {
        list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/dealers.txt"));
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
        setChoiceData();
    }


}
