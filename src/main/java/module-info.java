module com.example.fooddistributionmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.fooddistributionmanager to javafx.fxml;
    exports com.example.fooddistributionmanager;
}