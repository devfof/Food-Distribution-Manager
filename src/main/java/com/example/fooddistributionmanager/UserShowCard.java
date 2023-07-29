package com.example.fooddistributionmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserShowCard {

    @FXML
    private Label account;

    @FXML
    private Label email;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    private User user;

    public void setData(User user){
        this.user = user;
        this.email.setText(user.getEmail());
        this.name.setText(user.getName());
        this.account.setText(user.getAccount());
        this.phone.setText(user.getPhone());
    }
}
