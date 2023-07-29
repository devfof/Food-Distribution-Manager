package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class WorkerShowCard {
    @FXML
    private Label date;

    @FXML
    private Label division;

    @FXML
    private Label onion;

    @FXML
    private Label potato;

    @FXML
    private Label rice;

    @FXML
    private Label soybeanOil;

    public void setData(Food food){
        this.date.setText(food.getMonth()+"/"+food.getYear());
        this.division.setText(food.getDivision());
        this.rice.setText(food.getRice()+"");
        this.soybeanOil.setText(food.getSoybeanOil()+"");
        this.onion.setText(food.getOnion()+"");
        this.potato.setText(food.getPotato()+"");
    }
}
