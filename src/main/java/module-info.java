module com.example.skiapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.skiapp to javafx.fxml;
    exports com.example.skiapp;
}