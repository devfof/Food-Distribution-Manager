package com.example.fooddistributionmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class UserShow implements Initializable{
    List<User> list ;
    User user;
    @FXML
    private VBox addCards;
    @FXML
    private ScrollPane showUser;

    public void show(){
        getUser();
        showCards();
    }


    private void showCards() {
        for (User u: list){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserShowCard.fxml"));
                Pane root = fxmlLoader.load();
                addCards.getChildren().add(root);

                UserShowCard controller =fxmlLoader.getController();
                controller.setData(u);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void getUser() {
        list = new ArrayList<>();
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

            Collections.sort(list, new Comparator<User>() {
                @Override
                public int compare(User o1, User o2) {
                    return o1.getName().compareTo(o2.getName());
                }
            });

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        show();
    }
}
