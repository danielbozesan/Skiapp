package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class SessionController {

    @FXML
    private Text showsessionMessage;

    @FXML
    public void handleSessionAction() {
        showsessionMessage.setText("Session finished!");
    }
}