package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class FirstSceneController implements Initializable {
    private static Long loggedUserId;
    private static String username, password;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    
    
    @FXML
    public void loginSetOnAction(ActionEvent event){
        errorLabel.setText("");
        
        if(!usernameField.getText().isEmpty() && !passwordField.getText().isEmpty()){
            username = String.valueOf(usernameField.getText());
            password = hash(String.valueOf(passwordField.getText()));
            loggedUserId = MainApp.db.login(username, password);
            
            if(loggedUserId > 0){
                MainApp.username = username;
                MainApp.loggedUserId = loggedUserId;
                usernameField.clear();
                passwordField.clear();
                MainApp.homeController.setParameters(username, password, loggedUserId);
                MainApp.getStage().setScene(MainApp.homeScene);
                
            }            
            else{
                errorLabel.setText("Username or Password not correct. Please, try again. ");
            }
        }
        else{
            errorLabel.setText("Insert all the required fields. ");
        }
 
    }
    
    public void registrationSetOnAction(ActionEvent event){
        errorLabel.setText("");
        MainApp.getStage().setScene(MainApp.registrationScene);
    }
    
    private String hash(String psw){
        byte[] hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(psw.getBytes("UTF-8")); 
            return DatatypeConverter.printHexBinary(hash);
        }catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        
    }
       
}
