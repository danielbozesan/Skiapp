package org.loose.fis.registration.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.loose.fis.registration.example.services.UserService;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //UserService.loadUsersFromFile();
        UserService.initDatabase();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Welcome.fxml"));
        primaryStage.setTitle("SkiApp");
        primaryStage.setScene(new Scene(root, 600, 575));
        primaryStage.show();
    }
}
