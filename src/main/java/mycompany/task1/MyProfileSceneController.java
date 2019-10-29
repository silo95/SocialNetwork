package mycompany.task1;

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javax.xml.bind.*;

public class MyProfileSceneController {
    public static Long loggedUserId;
    private DBManager db;
    public static String username, password;
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label errorLabel;
    
    public MyProfileSceneController(){
        db = MainApp.db;       
        username = MainApp.username;
        loggedUserId = MainApp.loggedUserId;   
    }

    
    public void backSetOnAction(ActionEvent event){
        MainApp.getStage().setScene(MainApp.homeScene);
    }
    
    @FXML
    public void saveButtonOnAction(){
  
    }

    
    private String hash(String psw){
        byte[] hash;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hash = digest.digest(psw.getBytes("UTF-8")); 
            return DatatypeConverter.printHexBinary(hash);
        }catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {

    }     
}
