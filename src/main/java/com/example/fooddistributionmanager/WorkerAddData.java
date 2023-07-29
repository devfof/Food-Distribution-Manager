package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class WorkerAddData implements Initializable {
    List<Food> list = new ArrayList<>();
    Food food;


    @FXML
    private Label AlertDisplay;

    @FXML
    private ChoiceBox<String> newDivisionChoice;

    @FXML
    private TextField newMonth;
    @FXML
    private TextField newOnion;

    @FXML
    private TextField newPotato;

    @FXML
    private TextField newRice;

    @FXML
    private TextField newSoybeanOil;

    @FXML
    private TextField newYear;

    @FXML
    void saveButton(ActionEvent event) {
        if (newMonth.getText().equals("") || newYear.getText().equals("") || newOnion.getText().equals("") || newPotato.getText().equals("") || newRice.getText().equals("") || newSoybeanOil.getText().equals("")){
            AlertDisplay.setText("Empty field!");
            AlertDisplay.setStyle("-fx-text-fill: red;");
        } else if ( 1900 > Integer.parseInt(newYear.getText()) || 2022 < Integer.parseInt(newYear.getText()) || 0 >= Integer.parseInt(newMonth.getText()) || 12 < Integer.parseInt(newMonth.getText())){
            AlertDisplay.setText("Enter date correctly");
            AlertDisplay.setStyle("-fx-text-fill: red;");
        } else if (0 > Double.parseDouble(newOnion.getText()) || 0 > Double.parseDouble(newPotato.getText()) || 0 > Double.parseDouble(newRice.getText()) || 0 > Double.parseDouble(newSoybeanOil.getText())){
            AlertDisplay.setText("Enter food Value Correctly");
            AlertDisplay.setStyle("-fx-text-fill: red;");
        } else {
            getFoodInfo();
            saveData();
            AlertDisplay.setText("Saved");
            AlertDisplay.setStyle("-fx-text-fill: green;");
            newMonth.setText("");
            newOnion.setText("");
            newPotato.setText("");
            newRice.setText("");
            newSoybeanOil.setText("");
            newYear.setText("");
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
    private void getFoodInfo() {
        food = new Food();
        food.setYear(Integer.parseInt(newYear.getText()));
        food.setMonth(Integer.parseInt(newMonth.getText()));
        food.setDivision(newDivisionChoice.getValue());
        food.setRice(Double.parseDouble(newRice.getText()));
        food.setSoybeanOil(Double.parseDouble(newSoybeanOil.getText()));
        food.setOnion(Double.parseDouble(newOnion.getText()));
        food.setPotato(Double.parseDouble(newPotato.getText()));
        list.add(food);
    }
    private void getFood() {
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
    private void setChoiceData() {
        newDivisionChoice.getItems().addAll("Barisal","Chittagong","Dhaka","Khulna","Mymensingh","Rajshahi","Rangpur","Sylhet");
        newDivisionChoice.setValue("Dhaka");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getFood();
        setChoiceData();
    }


}
