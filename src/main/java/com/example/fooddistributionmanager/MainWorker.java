package com.example.fooddistributionmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWorker implements Initializable {
    MainApplication main = new MainApplication();
    User currentUser = main.getCurrentUser();
    @FXML
    private Button addData;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ImageView displayImage;

    @FXML
    private Label displayName;

    @FXML
    private Button history;

    @FXML
    void addDataBTN(ActionEvent event) {
        changeView("WorkerAddData.fxml");
    }

    @FXML
    void historyBTN(ActionEvent event) {
        changeView("WorkerShow.fxml");
    }

    @FXML
    void homeOpen(MouseEvent event) {
        changeView("MainWorker.fxml");
        borderPane.setBottom(null);
    }
    @FXML
    void MenuOpen(MouseEvent event) {
        changeView("Settings.fxml");
    }

    public void changeView(String fxml){
        displayName.setText(currentUser.getName());
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxml));
            borderPane.setCenter(root);
            borderPane.setTop(null);
            borderPane.setLeft(null);
            borderPane.setRight(null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayName.setText(currentUser.getName());
    }
}
