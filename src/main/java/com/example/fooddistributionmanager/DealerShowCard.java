package com.example.fooddistributionmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DealerShowCard {

    @FXML
    private Label division;

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private Label phone;

    public void setData(Dealer dealer){
        this.id.setText(dealer.getId());
        this.name.setText(dealer.getName());
        this.division.setText(dealer.getDivision());
        this.phone.setText(dealer.getPhone());
    }
}
