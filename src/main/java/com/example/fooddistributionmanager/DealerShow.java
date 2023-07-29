package com.example.fooddistributionmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.*;

public class DealerShow implements Initializable{
    List<Dealer> list ;
    Dealer dealer;
    @FXML
    private VBox addCards;
    @FXML
    private ScrollPane showUser;

    public void show(){
        getUser();
        showCards();
    }


    private void showCards() {
        for (Dealer d: list){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DealerShowCard.fxml"));
                Pane root = fxmlLoader.load();
                addCards.getChildren().add(root);

                DealerShowCard controller =fxmlLoader.getController();
                controller.setData(d);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void getUser() {
        list = new ArrayList<>();
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
            Collections.sort(list, new Comparator<Dealer>() {
                @Override
                public int compare(Dealer o1, Dealer o2) {
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
