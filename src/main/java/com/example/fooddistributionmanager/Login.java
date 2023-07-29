package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Login {
    MainApplication main = new MainApplication();
    private static User user;
    @FXML
    private Button loginButton;

    @FXML
    private Label loginDisplay;

    @FXML
    private TextField userMail;

    @FXML
    private PasswordField userPassword;

    @FXML
    void forgotPassword(ActionEvent event) {
        main.changeScene("LoginForgotPassword.fxml");
    }
    @FXML
    void userLogin(ActionEvent event) {
        if (userMail.equals("") || userPassword.equals("")) {
            loginDisplay.setText("Please enter your data");
        } else {
            try{
                checkLogin();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private void checkLogin() throws IOException {
        Socket socket = new Socket("localhost",2222);
        System.out.println("Client Connected");
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        String msg = userMail.getText()+"&%&"+userPassword.getText();
        oos.writeObject(msg);

        try {
            String message = (String) ois.readObject();
            if (message.equals("admin")) {
                message = (String)ois.readObject();
                System.out.println(message);
                user = splitUserData(message);
                main.setCurrentUser(user);
                main.changeScene("MainAdmin.fxml");

            }else if (message.equals("worker")) {
                message = (String)ois.readObject();
                System.out.println(message);
                user = splitUserData(message);
                main.setCurrentUser(user);
                main.changeScene("MainWorker.fxml");
            } else {
                loginDisplay.setText(message);
            }
            socket.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private User splitUserData(String u) {
        String[] parts = u.split("&%&");
        user = new User();
        user.setEmail(parts[0]);
        user.setPassword(parts[1]);
        user.setName(parts[2]);
        user.setAccount(parts[3]);
        user.setPhone(parts[4]);
        return user;
    }
}
