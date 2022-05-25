package org.loose.fis.registration.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.registration.example.exceptions.IncorrectLogin;
import org.loose.fis.registration.example.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.registration.example.services.UserService;
import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Admin");
    }

    @FXML
    public void handleLoginAction() {
        int ok=1;
        /*
        if(!UserService.checkUserLogin(usernameField.getText(), passwordField.getText()))
            loginMessage.setText("Login failed!");
        else
            loginMessage.setText("Login succesful!");
         */
        if(ok==1){
            Parent root;
            try {

                root = FXMLLoader.load(getClass().getClassLoader().getResource("HomeClient.fxml"));
                Stage stage=new Stage();
                stage.setTitle("SkiApp");
                stage.setScene(new Scene(root,600,575));
                stage.show();

            }
            catch(IOException e)
            {
                e.printStackTrace();
            }}
        else{
        /*
            String r= (String) role.getValue();
            if(UserService.checkUserLogin(usernameField.getText(), passwordField.getText(), (String) r)==1)
                loginMessage.setText("Client logged in successfully!");
            else
                if(UserService.checkUserLogin(usernameField.getText(), passwordField.getText(), (String) r)==2)
                    loginMessage.setText("Admin logged in successfully!");
                else
                    loginMessage.setText("Login failed!");
*/

        try {
            String r=UserService.getRoleByUsername(usernameField.getText());
            UserService.checkUserLogin(usernameField.getText(), passwordField.getText(), (String) r);
            loginMessage.setText("Login successfully!");
            Parent root;

            String A = new String();
            A="Admin";

            if (Objects.equals(role,A))
            {
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeAdmin.fxml"));
                // etc.

                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeClient.fxml"));
                String username = usernameField.getText();
                root = (Parent) loader.load();
            }
            else
            {
                //FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeClient.fxml"));
                // etc.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/openClient.fxml"));
                root = (Parent) loader.load();
            }
            Stage stage=new Stage();
            stage.setTitle("SkiApp");
            stage.setScene(new Scene(root,600,575));
            stage.show();

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        catch (IncorrectLogin e) {
            loginMessage.setText(e.getMessage());
        }}
    }
}