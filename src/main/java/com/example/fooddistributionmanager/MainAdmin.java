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

public class MainAdmin implements Initializable {
    MainApplication main = new MainApplication();
    User currentUser = main.getCurrentUser();
    @FXML
    private BorderPane borderPane;
    @FXML
    private Button dealerManager;
    @FXML
    private ImageView displayImage;
    @FXML
    private Button userManager;
    @FXML
    private Button switchMode;
    @FXML
    private Button dashboard;


    @FXML
    private Label displayName;
    @FXML
    void DealerManager(ActionEvent event) {
        dealerManager.setStyle("-fx-background-color: #fafafa; -fx-text-fill: #005691");
        dashboard.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        switchMode.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        userManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        changeView("DealerManager.fxml");
    }

    @FXML
    void Dashboard(ActionEvent event) {
        dashboard.setStyle("-fx-background-color: #fafafa; -fx-text-fill: #005691");
        userManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        switchMode.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        dealerManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        changeView("Dashboard.fxml");
    }

    @FXML
    void UserManager(ActionEvent event) {
        userManager.setStyle("-fx-background-color: #fafafa; -fx-text-fill: #005691");
        dashboard.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        switchMode.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        dealerManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        changeView("UserManager.fxml");
    }
    @FXML
    void SwitchMode(ActionEvent event) {
        switchMode.setStyle("-fx-background-color: #fafafa; -fx-text-fill: #005691");
        dashboard.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        userManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        dealerManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        MainApplication mainApplication=new MainApplication();
        mainApplication.changeScene("SwitchMode.fxml");
    }
    @FXML
    void MenuOpen(MouseEvent event) {
        dashboard.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        userManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        switchMode.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        dealerManager.setStyle("-fx-background-color: #005691; -fx-text-fill: #fafafa");
        changeView("Settings.fxml");
    }

    public void changeView(String fxml){

        displayName.setText(currentUser.getName());
        try {
            Pane root = FXMLLoader.load(getClass().getResource(fxml));
            borderPane.setCenter(root);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dashboard.setStyle("-fx-background-color: #fafafa; -fx-text-fill: #005691");
        displayName.setText(currentUser.getName());
        changeView("Dashboard.fxml");
    }
}
