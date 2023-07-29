package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.*;

public class WorkerShow implements Initializable{
    List<Food> list ;
    Food food;

    @FXML
    private Label alert;
    @FXML
    private BorderPane borderPane;
    @FXML
    private ChoiceBox<String> removeDivisionChoice;
    @FXML
    private TextField removeMonth;

    @FXML
    private TextField removeYear;

    @FXML
    void removeBTN(ActionEvent event) {
        for (Food d: list){
            if (removeDivisionChoice.getValue().equals(d.getDivision()) && d.getYear() == Integer.parseInt(removeYear.getText()) && d.getMonth() == Integer.parseInt(removeMonth.getText())) {
                list.remove(d);
                saveData();
                refresh();
                alert.setText("Deleted");
                alert.setStyle("-fx-text-fill: green;");
                removeMonth.setText(null);
                removeYear.setText(null);
                break;
            }else {
                alert.setText("enter data correctly");
                alert.setStyle("-fx-text-fill: red;");
            }
        }
    }

    private void getFoodData() {
        list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/foods.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("&%&");
                food = new Food();
                food.setYear(Integer.parseInt(parts[0]));
                food.setMonth(Integer.parseInt(parts[1]));
                food.setDivision(parts[2]);
                food.setRice(Double.parseDouble(parts[3]));
                food.setSoybeanOil(Double.parseDouble(parts[4]));
                food.setOnion(Double.parseDouble(parts[5]));
                food.setPotato(Double.parseDouble(parts[6]));
                list.add(food);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        try {
            FileWriter fr = new FileWriter("src/main/resources/Data/foods.txt");
            BufferedWriter writer = new BufferedWriter(fr);
            for (Food d: list){
                writer.write(d.getYear()+"&%&" + d.getMonth() + "&%&" + d.getDivision() +  "&%&" + d.getRice() +  "&%&" + d.getSoybeanOil() +  "&%&" + d.getOnion() +  "&%&" + d.getPotato()+ "\n");
            }
            writer.close();
            fr.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refresh(){
        getFoodData();
        try {
            Pane root = FXMLLoader.load(getClass().getResource("WorkerShowArea.fxml"));
            borderPane.setCenter(root);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setChoiceData() {
        removeDivisionChoice.getItems().addAll("Barisal","Chittagong","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet");
        removeDivisionChoice.setValue("Dhaka");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChoiceData();
        refresh();
    }
}
