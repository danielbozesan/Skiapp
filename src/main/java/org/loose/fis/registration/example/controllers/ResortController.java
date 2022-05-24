package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class ResortController {

    @FXML
    private Text resortMessage;


    @FXML
    public void handleResortAction() {
        resortMessage.setText("Details about resort");
    }
}