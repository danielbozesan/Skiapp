package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class VisitController {

    @FXML
    private Text visitMessage;


    @FXML
    public void handleVisitAction() {
        visitMessage.setText("Friends list");
    }
}
