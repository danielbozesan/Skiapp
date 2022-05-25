package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeClientController {
    public void handleClickSkiResorts(){


        Parent root;
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("SkiResorts.fxml"));
            Stage stage=new Stage();
            stage.setTitle("SkiApp");
            stage.setScene(new Scene(root,600,575));
            stage.show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleClickBuySkipass(){


        Parent root;
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("SkiPass.fxml"));
            Stage stage=new Stage();
            stage.setTitle("SkiApp");
            stage.setScene(new Scene(root,600,575));
            stage.show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void handleClickSkiStats(){


        Parent root;
        try {

            root = FXMLLoader.load(getClass().getClassLoader().getResource("SkiStats.fxml"));
            Stage stage=new Stage();
            stage.setTitle("SkiApp");
            stage.setScene(new Scene(root,600,575));
            stage.show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
