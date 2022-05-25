package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ReserveController {

    @FXML
    private Text reserveMessage;

    @FXML
    public void handleReserveAction() {
        reserveMessage.setText("Reservation completed!");
    }
}