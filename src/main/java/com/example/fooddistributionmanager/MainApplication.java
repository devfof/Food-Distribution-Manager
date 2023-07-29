package com.example.fooddistributionmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    private static Stage mainStage;
    private static Scene loginScene,replaceScene;
    private static User currentUser;
    private static String foundUser;

    public String getFoundUser() {
        return foundUser;
    }

    public void setFoundUser(String foundUser) {
        MainApplication.foundUser = foundUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        MainApplication.currentUser = currentUser;
    }

    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader loginFxmlLoader = new FXMLLoader(MainApplication.class.getResource("login.fxml"));
        loginScene = new Scene(loginFxmlLoader.load(), 950, 600);
        stage.setTitle("Food Distribution Manager");
        stage.setScene(loginScene);
        stage.show();
    }

    public void changeScene(String newScene){
        FXMLLoader newFxmlLoader = new FXMLLoader(MainApplication.class.getResource(newScene));
        try {
            replaceScene = new Scene(newFxmlLoader.load(), 950, 600);
            mainStage.setScene(replaceScene);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}