package com.example.fooddistributionmanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class DashboardShow {
    List<Food> list ;
    Food food;
    @FXML
    private LineChart<String, Double> chartShow;

    @FXML
    private VBox history;

    public void show(String value) {
        // history data
        getFoodData(value);
        Collections.sort(list, new Comparator<Food>() {
            @Override
            public int compare(Food o1, Food o2) {
                if (o1.getYear() < o2.getYear()) {
                    if (o1.getMonth() < o2.getMonth()) return 1;
                }
                if (o1.getYear() > o2.getYear()) {
                    if (o1.getMonth() > o2.getMonth()) return -1;
                }
                return 0;
            }
        });
        showCards();

        // line chart data
        showLineChart();


    }

    private void showLineChart() {
        chartShow.getData().clear();

        XYChart.Series<String,Double> riceLine = new XYChart.Series<String,Double>();
        XYChart.Series<String,Double> soybeanOilLine = new XYChart.Series<String,Double>();
        XYChart.Series<String,Double> onionLine = new XYChart.Series<String,Double>();
        XYChart.Series<String,Double> potatoLine = new XYChart.Series<String,Double>();

        riceLine.setName("Rice");
        soybeanOilLine.setName("SoybeanOil");
        onionLine.setName("onion");
        potatoLine.setName("potato");

        for (Food f: list){
            riceLine.getData().add(new XYChart.Data<String , Double>(f.getMonth()+"/"+f.getYear(),f.getRice()));
            soybeanOilLine.getData().add(new XYChart.Data<String , Double>(f.getMonth()+"/"+f.getYear(),f.getSoybeanOil()));
            onionLine.getData().add(new XYChart.Data<String , Double>(f.getMonth()+"/"+f.getYear(),f.getOnion()));
            potatoLine.getData().add(new XYChart.Data<String , Double>(f.getMonth()+"/"+f.getYear(),f.getPotato()));
        }

        chartShow.getData().addAll(riceLine,soybeanOilLine,onionLine,potatoLine);



    }


    private void showCards() {
        for (Food d: list){
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("WorkerShowCard.fxml"));
                Pane root = fxmlLoader.load();
                history.getChildren().add(root);

                WorkerShowCard controller =fxmlLoader.getController();
                controller.setData(d);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void getFoodData(String value) {
        list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/Data/foods.txt"));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split("&%&");
                if (value.equals(parts[2])) {
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

            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
